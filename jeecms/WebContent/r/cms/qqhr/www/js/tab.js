/**
 * @Author: 焦质晔
 * @Date: 2018/12/10
 * @Last Modified by: 焦质晔
 * @Last Modified time: 2018/12/10
 * 依赖 jQuery
 */
var Tab = (function(window, $, undefined){
    // 三段式
    // 1. 构造函数
    var Obj = function(option){
        // 在构造函数中处理属性
        this.$tabList = option.tabList
        this.$tabBox  = option.tabBox
        this.eventType = option.eventType || 'mouseenter'
        this.curIndex = parseInt(option.curIndex) || 0
        this.callback = option.callback || null
        
        if (!(this.$tabList && this.$tabList.length) || !(this.$tabBox && this.$tabBox.length)){
            throw new Error('组件参数有误~~~')
        }

        this.Len = this.$tabList.length

        this.install()
    };

    // 2. 原型对象
    Obj.prototype = {
        constructor: Obj,
        install: function(){
            // 初始化选项卡
            this.cutTabStyle()
            this.cutTabBox()
            // 绑定事件
            this.bindEvent()
        },
        bindEvent: function(){
            var _this = this
            for (var i = 0; i < this.Len; i++){
                this.$tabList.eq(i).on(this.eventType, function(ev){
                    _this.curIndex = $(this).index()
                    _this.cutTabStyle()
                    _this.cutTabBox()
                    // 执行回调
                    _this.callback && _this.callback(_this.curIndex)
                });
            }
        },
        cutTabStyle: function(){
            for (var i = 0; i < this.Len; i++){
                this.$tabList.eq(i).removeClass('selected')
            }
            this.$tabList.eq(this.curIndex).addClass('selected')
        },
        cutTabBox: function(){
            for (var i = 0; i < this.Len; i++){
                this.$tabBox[i].style.display = 'none'
            }
            this.$tabBox[this.curIndex].style.display = 'block'
        },
        removeEvent: function(){
            for (var i = 0; i < this.Len; i++){
                this.$tabList.eq(i).off(this.eventType)
            }
        },
        remove: function(){ // 处理组件移除的(释放内存)
            // 1. 解绑事件
            this.removeEvent()
            // 2. 有动态添加的DOM节点，移除节点

            // 3. 释放内存
            for (var attr in this){
                this[attr] = null
            }
        }
    };

    // 公开接口(内部构造函数)
    return Obj;
})(window, jQuery);
