/**
 * @Author: 焦质晔
 * @Date: 2018/12/10
 * @Last Modified by: 焦质晔
 * @Last Modified time: 2018/12/10
 * 依赖 jQuery
 */
var Swiper = (function(window, $, undefined){
    // 三段式
    // 1. 构造函数
    var Obj = function(option){
        // 在构造函数中处理属性
        this.$wrapper = option.wrapper
        
        if (!(this.$wrapper && this.$wrapper.length)){
            throw new Error('组件参数有误~~~')
        }

        this.curIndex = 0
        this.state = 'ready'
        this.isAutoPlay = typeof option.isAutoPlay == 'undefined' ? true : option.isAutoPlay
        this.timeLen = option.timeLen || 4
        this.timer = null

        this.$swiperList = this.$wrapper.find('.swiper-wrapper > .swiper-slide')
        this.$pagination = this.$wrapper.find('.swiper-pagination')
        this.Len = this.$swiperList.length
        if (this.Len == 0) return

        this.install()
    };

    // 2. 原型对象
    Obj.prototype = {
        constructor: Obj,
        install: function(){
            // 初始化选项卡
            this.initialFunc()
            // 绑定事件
            this.bindEvent()
            // 自动播放
            this.play()
        },
        play: function(){
            if (!this.isAutoPlay) return
            var _this = this
            this.stop()
            this.timer = setInterval(function(){
                if (++_this.curIndex >= _this.Len){
                    _this.curIndex = 0
                }
                _this.move()
            }, this.timeLen * 1000)
        },
        stop: function(){
            this.timer && clearInterval(this.timer)
        },
        move: function(){
            var _this = this
            if (this.state !== 'ready') return
            this.state = 'stop'

            this.$swiperList.eq(this.curIndex).addClass('active').
            velocity({opacity: 1}, {
                duration: 600,
                complete: function(){
                    _this.state = 'ready'
                }
            }).siblings().removeClass('active').velocity({opacity: 0}, {duration: 600})

            this.$pagination.find('i').eq(this.curIndex).addClass('selected').
            siblings().removeClass('selected')
        },
        bindEvent: function(){
            var _this = this
            if (this.$pagination && this.$pagination.length > 0){
                this.$pagination.on('mouseenter', 'i', function(){
                    _this.curIndex = $(this).index()
                    _this.move()
                })
            }
            this.$wrapper.on('mouseenter', function(){
                _this.stop()
            })
            this.$wrapper.on('mouseleave', function(){
                _this.play()
            })
        },
        initialFunc: function(){
            this.$swiperList.eq(this.curIndex).addClass('active').siblings().css('opacity', '0')
            if (this.$pagination && this.$pagination.length > 0){
                var str = ''
                for (var i = 0; i < this.Len; i++){
                    if (i === this.curIndex){
                        str += '<i class="selected"></i>'
                    } else {
                        str += '<i></i>'
                    }
                }
                this.$pagination.html(str)
            }
        },
        remove: function(){ // 处理组件移除的(释放内存)
            // 1. 解绑事件
            this.$wrapper.off('mouseenter')
            this.$wrapper.off('mouseleave')
            this.$pagination.off('mouseenter')
            
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
