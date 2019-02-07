/**
 * @version 1.00
 * @author  焦质晔
 * @update  2014-10-30
 */
function Alert(options){
	return new Alert.prototype.init(options);
};
Alert.prototype = {
	version: '1.00',
	author: 'jzy',
	construtor: Alert, //修正构造函数指针
	init: function(options){
		this.oCont    = options.outerContObj || document.body;
		this.msgTitle = options.ATitle || '提示信息';
		this.msgText  = options.AMsg || '...';
		this.btnArr   = options.ABtnArr || [{name: '确认', className: '', callback: null}];

		//alert DOM 结构初始化
		var alertHTML = [
				'<div class="alertWrapper">',
					'<div class="alertBox" id="alertBox">',
						'<div class="alertTitle">',
							'<h1 class="alertTitleText">', this.msgTitle , '</h1>',
							'<span class="alertClose">+</span>',
						'</div>',
						'<div class="alertContent">',
							'<p class="alertContentP">', this.msgText , '</p>',
						'</div>',
						'<div class="alertAction" id="alertAction">',
						'</div>',
					'</div>',
				'</div>'
			].join('');
		
		//执行execu方法
		this.execu(alertHTML);
	},
	execu: function(str){
		//判断节点是否存在
		if (PG.getEle('.alertWrapper', this.oCont)) return false;

		//初始化一些变量
		var thisTemp    = this;
		var AniEndArr   = [{b: 'webkit', e: 'webkitAnimationEnd'}, {b: 'firefox', e: 'animationend'}, {b: 'trident', e: 'animationend'}];
		var supportCss3 = PG.supportCss('transition'); //判断是否支持css3属性
		
		//插入节点
		PG.append(this.oCont, str);
		
		var oAlertBox   = PG._$('alertBox');
		var oAction     = PG._$('alertAction');

		//整体阻止事件冒泡
		oAlertBox.parentNode.onclick = function(ev){
			var oEvent = ev || event;
			PG.preventDefault(oEvent);
			PG.stopPropagation(oEvent);
		};

		for (var i = 0; i < this.btnArr.length; i++){ //处理按钮
			var oNewA       = PG.DC('a');
			oNewA.className = 'alertBtn ' + this.btnArr[i].className;
			oNewA.innerHTML = this.btnArr[i].name;
			oNewA.fnEnd     = this.btnArr[i].callback;
			oNewA.onclick   = function(){btnClick.call(this)}; //给按钮添加点击事件
			oAction.appendChild(oNewA);
		}

		//给右上角关闭按钮绑定关闭事件
		PG.getEle('.alertClose', oAlertBox)[0].onclick = close;

		//按钮事件
		function btnClick(){
			var _this = this;
			//执行关闭
			close();
			
			//执行回调函数
			if (supportCss3){
				for (var i = 0; i < AniEndArr.length; i++){
					if (navigator.userAgent.toLowerCase().search(AniEndArr[i].b) != -1){
						oAlertBox.addEventListener(AniEndArr[i].e, callBackEnd, false);
						break;
					}
				}
			} else {
				callBackEnd();
			}
			
			//回调函数
			function callBackEnd(){
				if (_this.fnEnd) _this.fnEnd();
			}
		}

		//关闭方法
		function close(){
			setTimeout(function(){
				PG.addClass(oAlertBox, 'out');
			}, 0);
			
			if (supportCss3){
				for (var i = 0; i < AniEndArr.length; i++){
					if (navigator.userAgent.toLowerCase().search(AniEndArr[i].b) != -1){
						oAlertBox.addEventListener(AniEndArr[i].e, removeEle, false);
						break;
					}
				}
			} else {
				removeEle();
			}
		}

		//移除节点
		function removeEle(){
			thisTemp.oCont.removeChild(oAlertBox.parentNode);
			//解除 Alert 对象的引用
			thisTemp = null;
		}
	}
};
Alert.prototype.init.prototype = Alert.prototype;
