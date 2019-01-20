/**
 * @Author: 焦质晔
 * @Date: 2019/1/16
 * @Last Modified by: 焦质晔
 * @Last Modified time: 2019/1/16
 * 依赖 jQuery
 */
var SubNav = (function(window, $, undefined){
    // 三段式
    // 1. 构造函数
    var Obj = function(option){
        // 在构造函数中处理属性
        this.$wrapper = option.wrapper
        this.callback = option.callback || null
        
        if (!(this.$wrapper && this.$wrapper.length)){
            throw new Error('组件参数有误~~~')
        }

        this.$navList = this.$wrapper.children('li')
        this.timer = null

        this.install()
    };

    // 2. 原型对象
    Obj.prototype = {
        constructor: Obj,
        install: function(){
            this.setStyle()
            this.bindEvent()
        },
        showSubNav: function($el){
            $el.stop().slideDown(200)
        },
        hideSubNav: function($el){
            $el.slideUp(200)
        },
        bindEvent: function(){
            var _this = this
            this.$navList.on('mouseenter', function(){
                var $this = $(this)
                var $subNavBox = $this.children('.sub-nav')
                if (!$subNavBox.length) return
                // 防抖
                _this.debounce(_this.showSubNav, 300)($subNavBox)
            })
            this.$navList.on('mouseleave', function(){
                var $this = $(this)
                var $subNavBox = $this.children('.sub-nav')
                if (!$subNavBox.length) return
                _this.clearTimer()
                _this.hideSubNav($subNavBox)
            })
        },
        // 函数防抖
        debounce: function(fn, delay){
            var _this = this
            return function(){
                var args = arguments
                _this.timer && clearTimeout(_this.timer)
                _this.timer = setTimeout(function(){ fn.apply(_this, args) }, delay)
            }
        },
        clearTimer: function(){
            this.timer && clearTimeout(this.timer)
        },
        setStyle: function(){
            this.$wrapper.css({ 'position': 'relative' })
            this.$navList.css({ 'position': 'static' })
        },
        remove: function(){ // 处理组件移除的(释放内存)
            // 1. 解绑事件
            this.$navList.off('mouseenter')
            this.$navList.off('mouseleave')
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
