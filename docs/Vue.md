# Vue 学习笔记

## 一、简介

Vue 是一套用于构建用户界面的**渐进式框架**。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层。

### 1. 安装

* 直接下载并用 `<script>` 标签引入，`Vue` 会被注册为一个全局变量。

* NPM 安装

  安装后本地就会有vue.js文件，使用`<script>` 标签引入即可

  ```
  $ npm install vue
   <script src="./node_modules/vue/dist/vue.js"></script>
  ```

  

### 2. 声明式渲染

* 创建vue实例，关联页面的模板，将自己的数据（data）渲染到关联的模板，响应式的
* 指令来简化对dom的一些操作。
* 声明方法来做更复杂的操作。methods里面可以封装方法。

```html
    <div id="app">
        <input type="text" v-model="num">
        <button v-on:click="num++">点赞</button>
        <button v-on:click="cancle">取消</button>
        <h1> {{name}} ,非常帅，有{{num}}个人为他点赞{{hello()}}</h1>
    </div>

    <script src="./node_modules/vue/dist/vue.js"></script>

    <script>
        //1、vue声明式渲染
        let vm = new Vue({
            el: "#app",//绑定元素
            data: {  //封装数据
                name: "张三",
                num: 1
            },
            methods:{  //封装方法
                cancle(){
                    this.num -- ;
                },
                hello(){
                    return "1"
                }
            }
        });
    </script>
```



### 3. 文本插值

数据绑定最常见的形式就是使用“Mustache”语法 (双大括号) 的文本插值：

```html
<span>Message: {{ msg }}</span>
```

Mustache 标签将会被替代为对应数据对象上 `msg` property 的值。无论何时，绑定的数据对象上 `msg` property 发生了改变，插值处的内容都会更新。

除此之外还可以使用表达式如`{{1+1}} `



## 二、指令

指令 (Directives) 是带有 `v-` 前缀的特殊 attribute。指令 attribute 的值预期是**单个 JavaScript 表达式** 。指令的职责是，当表达式的值改变时，将其产生的连带影响，响应式地作用于 DOM。

### 1. v-text、v-html

`v-text`：显示文本原始内容，不对html元素进行解析

`v-html`：显示html解析后的内容

```html
        <div id="app">
            <span v-html="msg"></span>	<!-- Hello(h1的大小) -->
            <br/>
            <span v-text="msg"></span> 	<!-- <h1>Hello</h1> -->

        </div>

        <script src="../node_modules/vue/dist/vue.js"></script>

        <script>
            new Vue({
                el:"#app",
                data:{
                    msg:"<h1>Hello</h1>",
                },
            })
        </script>
```



### 2. v-bind

* 单向绑定

  vue对象中的值变化会影响绑定标签，反之不会影响

* 缩写

  `<a v-bind:href="url">`可以缩写为`<a :href="url">`

* 属性绑定

  给html标签的属性绑定

  给class，style的值进行绑定： {class名：属性名(boolean)}/{style属性名：属性值}

```html
 <!-- 给html标签的属性绑定 -->
    <div id="app"> 

        <a v-bind:href="link">gogogo</a>

        <!-- 只有isActive、hasError为ture时，才有active和text-danger class-->
        <!-- style的color属性值为vue对象中color1的值 -->
        <span v-bind:class="{active:isActive,'text-danger':hasError}" 
                    :style="{color: color1,fontSize: size}">你好</span>
        <a v-bind:href="link">abc</a>

    </div>

    <script src="../node_modules/vue/dist/vue.js"></script>

    <script>
        let vm = new Vue({
            el:"#app",
            data:{
                link: "http://www.baidu.com",
                isActive:true,
                hasError:true,
                color1:'red',
                size:'36px'
            }
        })
    </script>
```



### 3. v-model

双向绑定，绑定的属性值会反过来影响vue对象中的值

```html
	<!-- 表单项，自定义组件 -->
    <div id="app">

        精通的语言：
            <input type="checkbox" v-model="language" value="Java"> java<br/>
            <input type="checkbox" v-model="language" value="PHP"> PHP<br/>
            <input type="checkbox" v-model="language" value="Python"> Python<br/>
        选中了 {{language.join(",")}}
    </div>
    
    <script src="../node_modules/vue/dist/vue.js"></script>

    <script>
        <!-- 当上面复选框被选中时，language的值就会变化-->
        let vm = new Vue({
            el:"#app",
            data:{
                language: []
            }
        })
    </script>
```



### 4. v-on

#### 4.1 监听事件

语法规则`v-on:[eventName]`

```html
<button v-on:click="num++">点赞</button>
```



#### 4.2 事件修饰符

修饰符是由点开头的指令后缀来表示的；

修饰符可以串联

- `.stop`   阻止单击事件继续传播
- `.prevent`    标签本身效果不会执行
- `.capture`    内部元素触发的事件先在此处理，然后才交由内部元素进行处理
- `.self`    event.target 是当前元素自身时触发处理函数
- `.once`   点击事件将只会触发一次

```html
<!-- 仅会触发一次点击事件 -->
<div style="border: 1px solid red;padding: 20px;" v-on:click.once="hello">
    大div
    <!-- 仅会触发小div的click事件，不会传播至大div的click事件 -->
    <div style="border: 1px solid blue;padding: 20px;" @click.stop="hello">
        小div <br />
        <!-- 同时触发大小div的click事件，但不会触发超链接 -->
        <a href="http://www.baidu.com" @click.prevent="hello">去百度</a>
    </div>
</div>
```



#### 4.3 按键修饰符

使用格式：`v-on:keyup.按键名`

当点击这些按键时才会触发事件

- `.enter`
- `.tab`
- `.delete` (捕获“删除”和“退格”键)
- `.esc`
- `.space`
- `.up`
- `.down`
- `.left`
- `.right`

```html
<!-- 按up num+2，按down num-2，同时点击和按ctrl num=10 -->
<input type="text" v-model="num" v-on:keyup.up="num+=2" @keyup.down="num-=2" @click.ctrl="num=10"><br />
```



### 5. v-for

遍历数组：`v-for="item in items`

若要获取数组下标：`v-for="(item,index) in items`

遍历对象：

```html
v-for="value in object"
v-for="(value,key) in object"
v-for="(value,key,index) in object" 
```

注意：遍历的时候都加上:key来区分不同数据，提高vue渲染效率

`v-for="(user,index) in users" :key="user.name"`

```html
<div id="app">
    <ul>
        <li v-for="(user,index) in users" :key="user.name">
            <!-- 1、显示user信息：v-for="item in items" -->
            当前索引：{{index}} ==> {{user.name}}  ==>   {{user.gender}} ==>{{user.age}} <br>
            <!-- 2、获取数组下标：v-for="(item,index) in items" -->
            <!-- 3、遍历对象：
            v-for="value in object"
            v-for="(value,key) in object"
            v-for="(value,key,index) in object" 
            -->
            对象信息：
            <span v-for="(v,k,i) in user">{{k}}=={{v}}=={{i}}；</span>
            <!-- 4、遍历的时候都加上:key来区分不同数据，提高vue渲染效率 -->
        </li>
    </ul>

    <ul>
        <li v-for="(num,index) in nums" :key="index">
            当前数字{{num}}
        </li>
    </ul>

    <ul>
        <li v-for="(user, index) in users" :key="index">
            <span v-for="(value,key,index) in user" :key="index">
                {{index}}
            </span> 
        </li>
    </ul>
</div>
<script src="../node_modules/vue/dist/vue.js"></script>
<script>         
    let app = new Vue({
        el: "#app",
        data: {
            users: [{ name: '柳岩', gender: '女', age: 21 },
                    { name: '张三', gender: '男', age: 18 },
                    { name: '范冰冰', gender: '女', age: 24 },
                    { name: '刘亦菲', gender: '女', age: 18 },
                    { name: '古力娜扎', gender: '女', age: 25 }],
            nums: [1,2,3,4,4]
        },
    })
</script>
```



### 6. v-if 和 v-show

`v-if`：条件判断。当得到结果为true时，所在的元素才会被渲染。

`v-show`：当得到结果为true时，所在的元素才会被显示，false时元素依然存在，只是添加了不显示的样式

```html
 <div id="app">
        <button v-on:click="show = !show">点我呀</button>
     	<!-- 当show为true时才会显示标签 -->
        <!-- 1、使用v-if显示 -->
        <h1 v-if="show">if=看到我....</h1>
        <!-- 2、使用v-show显示 -->
        <h1 v-show="show">show=看到我</h1>
    </div>

    <script src="../node_modules/vue/dist/vue.js"></script>
        
    <script>
        let app = new Vue({
            el: "#app",
            data: {
                show: true
            }
        })
    </script>
```



### 7. v-else和v-else-if

```html
<div id="app">
        <button v-on:click="random=Math.random()">点我呀</button>
        <span>{{random}}</span>
        <h1 v-if="random>=0.75">
            看到我啦？！ &gt;= 0.75
        </h1>
        <h1 v-else-if="random>=0.5">
            看到我啦？！ &gt;= 0.5
        </h1>
        <h1 v-else-if="random>=0.2">
            看到我啦？！ &gt;= 0.2
        </h1>
        <h1 v-else>
            看到我啦？！ &lt; 0.2
        </h1>
    </div>

    <script src="../node_modules/vue/dist/vue.js"></script>
        
    <script>         
        let app = new Vue({
            el: "#app",
            data: { random: 1 }
        })     
    </script>
```



## 三、计算属性和侦听器

### 1. 计算属性

某些结果是基于之前数据实时计算出来的，我们可以利用计算属性来完成

```html
<div id="app">

    <ul>
        <li>西游记； 价格：{{xyjPrice}}，数量：<input type="number" v-model="xyjNum"> </li>
        <li>水浒传； 价格：{{shzPrice}}，数量：<input type="number" v-model="shzNum"> </li>
        <!-- 计算得到的属性值 -->
        <li>总价：{{totalPrice}}</li>
        <li>总数量:{{totalNum}}</li>
        <!-- 显示侦听器的信息 -->
        {{msg}}
    </ul>
</div>
<script src="../node_modules/vue/dist/vue.js"></script>
<script>
    new Vue({
        el: "#app",
        data: {
            xyjPrice: 99.98,
            shzPrice: 98.00,
            xyjNum: 1,
            shzNum: 1,
            msg: ""
        },
        //计算属性，总价和总数量会根据实时数据进行计算得出
        computed: {
            totalPrice(){
                return this.xyjPrice*this.xyjNum + this.shzPrice*this.shzNum
            },
            totalNum(){
                return this.xyjNum*1+this.shzNum*1;
            }
        },
    })
</script>
```



### 2.  侦听器

Vue 通过 `watch` 选项提供了一个更通用的方法，来响应数据的变化。

```javascript
  new Vue({
            el: "#app",
            data: {
                xyjPrice: 99.98,
                shzPrice: 98.00,
                xyjNum: 1,
                shzNum: 1,
                msg: ""
            },
         	
      		......
         	//侦听shzNum值的变化
            watch:  {
                shzNum(newVal,oldVal){
                    if(newVal>=4){
                        this.msg="水浒传库存超出限制";
                        this.shzNum=4
                    }else{
                        this.msg=""
                    }
                }
            },
        })
```



### 3. 过滤器

用于一些常见的文本格式化。过滤器可以用在两个地方：**双花括号插值和 `v-bind` 表达式**。过滤器应该被添加在 JavaScript 表达式的尾部，由“管道”符号指示：

```html
<!-- 在双花括号中 -->
{{ message | capitalize }}

<!-- 在 `v-bind` 中 -->
<div v-bind:id="rawId | formatId"></div>
```

实例

```html
	<!-- 过滤器常用来处理文本格式化的操作。过滤器可以用在两个地方：双花括号插值和 v-bind 表达式 -->
    <div id="app">
        <ul>
            <li v-for="user in userList">
                <!-- 通过过滤器让性别正确显示 -->
                {{user.gender | genderFilter}} ==> {{user.gender | gFilter}}
            </li>
        </ul>
    </div>
    <script src="../node_modules/vue/dist/vue.js"></script>

    <script>
		//全局过滤器
        Vue.filter("gFilter", function (val) {
            if (val == 1) {
                return "男~~~";
            } else {
                return "女~~~";
            }
        })
    
        let vm = new Vue({
            el: "#app",
            data: {
                userList: [
                    { id: 1, name: 'jacky', gender: 1 },
                    { id: 2, name: 'peter', gender: 0 }
                ]
            },
            filters: {
                // filters 定义局部过滤器，只可以在当前vue实例中使用
                genderFilter(val) {
                    if (val == 1) {
                        return "男";
                    } else {
                        return "女";
                    }
                }
            }
        })
```



## 四、组件化

通过定义组件就可以通过组件名组件标签进行组件模板复用

```html
  <div id="app">
        <button v-on:click="count++">我被点击了 {{count}} 次</button>
        <!-- 定义的全局组件-->
      	<!-- 每个标签都相当于<button v-on:click="count++">我被点击了 {{count}} 次</button> -->
        <counter></counter>
        <counter></counter>
        <counter></counter>
  
		<!-- 定义的局部组件 -->
        <button-counter></button-counter>

    </div>
    <script src="../node_modules/vue/dist/vue.js"></script>


    <script>
        //1、全局声明注册一个组件
        Vue.component("counter", {
            template: `<button v-on:click="count++">我被点击了 {{count}} 次</button>`,
            data() {
                return {
                    count: 1
                }
            }
        });


        //2、局部声明一个组件
        const buttonCounter = {
            template: `<button v-on:click="count++">我被点击了 {{count}} 次~~~</button>`,
            data() {
                return {
                    count: 1
                }
            }
        };
        new Vue({
            el: "#app",
            data: {
                count: 1
            },
            components: {
                'button-counter': buttonCounter
            }
        })
    </script>
```



## 五、生命周期钩子函数

每个 Vue 实例在被创建时都要经过一系列的初始化过程——例如，需要设置数据监听、编译模板、将实例挂载到 DOM 并在数据变化时更新 DOM 等。同时在这个过程中也会运行一些叫做**生命周期钩子**的函数，这给了用户在不同阶段添加自己的代码的机会。

```html
 <div id="app">
        <span id="num">{{num}}</span>
        <button @click="num++">赞！</button>
        <h2>{{name}}，有{{num}}个人点赞</h2>
    </div>

    <script src="../node_modules/vue/dist/vue.js"></script>
    
    <script>
        let app = new Vue({
            el: "#app",
            data: {
                name: "张三",
                num: 100
            },
            methods: {
                show() {
                    return this.name;
                },
                add() {
                    this.num++;
                }
            },
            beforeCreate() {
                console.log("=========beforeCreate=============");
                console.log("数据模型未加载：" + this.name, this.num); //undefined
                console.log("方法未加载：" + this.show()); // Error in beforeCreate hook: 												"TypeError: this.show is not a function"
                console.log("html模板未加载：" + document.getElementById("num"));
            },
            created: function () {
                console.log("=========created=============");
                console.log("数据模型已加载：" + this.name, this.num); //张三 100
                console.log("方法已加载：" + this.show());		//张三
                console.log("html模板已加载：" + document.getElementById("num"));
                											//[object HTMLSpanElement]
                console.log("html模板未渲染：" + document.getElementById("num").innerText);
                											//{{num}}
            },
            beforeMount() {
                console.log("=========beforeMount=============");
                console.log("html模板未渲染：" + document.getElementById("num").innerText);
                															//{{num}}
            },
            mounted() {
                console.log("=========mounted=============");
                console.log("html模板已渲染：" + document.getElementById("num").innerText);
                															//100
            },
            
            <!-- 以下两个方法在模型变化后触发 -->
            beforeUpdate() {
                console.log("=========beforeUpdate=============");
                console.log("数据模型已更新：" + this.num);  				//101
                console.log("html模板未更新：" + document.getElementById("num").innerText);
            															 //100
            },
            updated() {
                console.log("=========updated=============");
                console.log("数据模型已更新：" + this.num);					//101
                console.log("html模板已更新：" + document.getElementById("num").innerText);
                														 //101
            }
        });
    </script>
```

[官方生命周期图](https://cn.vuejs.org/v2/guide/instance.html#实例生命周期钩子)