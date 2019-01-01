/**
 * @Author: 焦质晔
 * @Date: 2018/12/20
 * @Last Modified by: 焦质晔
 * @Last Modified time: 2018/12/20
 * 依赖 jQuery
 */
var Sidebar = (function(window, $, undefined){
    // 三段式
    // 1. 构造函数
    var Obj = function(option){
        // 在构造函数中处理属性
        this.$wrapper  = option.wrapper
        this.originTop = 0
        
        if (!(this.$wrapper && this.$wrapper.length)){
            throw new Error('组件参数有误~~~')
        }
        this.$navBar = this.$wrapper.children('.nav-bar')
        // 没有滑块，直接跳出
        if (!(this.$navBar && this.$navBar.length)) return

        this.install()
    };

    // 2. 原型对象
    Obj.prototype = {
        constructor: Obj,
        install: function(){
            this.initial()
            this.bindEvent()
        },
        initial: function(){
            var $selectedNode = this.$wrapper.find('li.selected')
            if ($selectedNode && $selectedNode.length > 0){
                this.originTop = $selectedNode[0].offsetTop
            } else {
                this.$wrapper.find('li').eq(0).addClass('selected')
            }
            this.move(this.originTop)
        },
        bindEvent: function(){
            var _this = this
            this.$wrapper.on('mouseenter', 'li', function(){
                _this.move(this.offsetTop)
            })
            this.$wrapper.on('mouseleave', function(){
                _this.move(_this.originTop)
            })
        },
        move: function(t){
            this.$navBar.css({ top: t })
        },
        remove: function(){ // 处理组件移除的(释放内存)
            // 1. 解绑事件
            this.$wrapper.off('mouseenter')
            this.$wrapper.off('mouseleave')
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
