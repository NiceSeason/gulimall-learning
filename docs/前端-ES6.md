# 一、简介

ES6， 全称 ECMAScript 6.0 ，是 JavaScript 的下一个版本标准，2015.06 发版。

ES6 主要是为了解决 ES5 的先天不足，比如 JavaScript 里并没有类的概念，但是目前浏览器的 JavaScript 是 ES5 版本，大多数高版本的浏览器也支持 ES6，不过只实现了 ES6 的部分特性和功能。



# 二、 特性

## 1. let

新增加的声明变量的关键字，与var声明变量有以下不同

* **声明的变量有严格局部作用域**

```javascript
   		{
            var a = 102;
            let b = 2;
        }
        console.log(a);  // 1
        console.log(b);  // ReferenceError: b is not defined
```



* **var 可以声明多次，let 只能声明一次**

```javascript
     	var m = 1
        var m = 2
        let n = 3
        let n = 4
        console.log(m)  // 2
        console.log(n)  // Identifier 'n' has already been declared
```



*  **var 会变量提升，let 不存在变量提升**

```javascript
  		console.log(x);  // undefined
        var x = 10;
        console.log(y);   //ReferenceError: y is not defined
        let y = 20;
```



## 2. const

* **一但声明必须初始化，否则会报错**
* **声明之后不允许改变**

```javascript
   		const a = 1;
        a = 3; //Uncaught TypeError: Assignment to constant variable.
```



## 3. 解构表达式

* **数组解构**

  可以直接将数组的元素依次赋值给其他变量

```javascript
	   let arr = [1,2,3];

		//不需要像下面这样挨个赋值
        // let a = arr[0];
        // let b = arr[1];
        // let c = arr[2];

        let [a,b,c] = arr;

        console.log(a,b,c) //1 2 3
```



* **对象解构**

     可以直接将对象的属性赋给其他变量

  ```javascript
   		const person = {
              name: "jack",
              age: 21,
              language: ['java', 'js', 'css']
          }
  
          //			不需要像下面这样挨个赋值
          //         const name = person.name;
          //         const age = person.age;
          //         const language = person.language;
  
  
          //对象解构
          //将name的值赋给abc
          const { name:abc, age, language } = person;
  
          console.log(abc, age, language)
  ```



## 4. 字符串

* **字符串扩展**

  字符串可以使用`startsWith()`、`endsWith`、`includes`等函数

  ```javascript
  		let str = "hello.vue";
          console.log(str.startsWith("hello"));//true
          console.log(str.endsWith(".vue"));//true
          console.log(str.includes("e"));//true
          console.log(str.includes("hello"));//true
  ```

* **模板字符串**

  模板字符串相当于加强版的字符串，用反引号 **`**,除了作为普通字符串，还可以用来定义多行字符串，还可以在字符串中加入变量和表达式。

  ```javascript
          普通字符串:
          let string = `Hello'\n'world`;
          console.log(string); 
          // "Hello'
          // 'world"
  
          多行字符串:
          let string1 =  `Hey,
          can you stop angry now?`;
          console.log(string1);
          // Hey,
          // can you stop angry now?
  
          变量名写在 ${} 中，${} 中可以放入 JavaScript 表达式:
          function fun() {
              return "这是一个函数"
          }
  
          let info = `我是${abc}，今年${age + 10}了, 我想说： ${fun()}`;
          console.log(info);
          //我是jack，今年31了, 我想说： 这是一个函数
  ```

  

## 5. 函数优化

* **参数默认值**

  在ES6以前，我们无法给一个函数参数设置默认值，只能采用变通写法；现在可以直接给参数写上默认值，没传就会自动使用默认值

  ```javascript
      	//在ES6以前，我们无法给一个函数参数设置默认值，只能采用变通写法：
          function add(a, b) {
              // 判断b是否为空，为空就给默认值1
              b = b || 1;
              return a + b;
          }
          // 传一个参数
          console.log(add(10));
  
  
          //现在可以这么写：直接给参数写上默认值，没传就会自动使用默认值
          function add2(a, b = 1) {
              return a + b;
          }
          console.log(add2(20));
  ```



* **不定参数**

  ```javascript
          function fun(...values) {
              console.log(values.length)
          }
          fun(1, 2)      //2
          fun(1, 2, 3, 4)  //4
  ```



* **箭头函数**

  ```javascript
          var print = obj => console.log(obj);
          print("hello");
  
  
     		//箭头函数+解构
          const person = {
              name: "jack",
              age: 21,
              language: ['java', 'js', 'css']
          }
          
          var hello2 = ({name}) => console.log("hello," +name);
          hello2(person);
  ```



## 6. 对象优化

* **对象的新方法**

  ```javascript
         const person = {
              name: "jack",
              age: 21,
              language: ['java', 'js', 'css']
          }
  		//key/values/entries方法
          console.log(Object.keys(person));//["name", "age", "language"]
          console.log(Object.values(person));//["jack", 21, Array(3)]
          console.log(Object.entries(person));//[Array(2), Array(2), Array(2)]
          
          const target = { a: 1 };
          const source1 = { b: 2 };
          const source2 = { c: 3 };
  
         	//assign将源对象的所有可枚举属性复制到目标对象中。
          Object.assign(target, source1, source2);
          console.log(target); //{a:1,b:2,c:3}
  ```



* **声明对象简写**

  ```javascript
          const age = 23
          const name = "张三"
          //原本声明对象
          const person1 = { age: age, name: name }
  		
          //简写后(若属性名与常量名相同时才可以使用)
          const person2 = { age, name }
          console.log(person2);
  ```



* **对象函数属性简写**

  可以省略对象函数属性的声明，用箭头函数或者函数属性名代替函数声明

  ```javascript
          let person3 = {
              name: "jack",
              // 以前：
              eat: function (food) {
                  console.log(this.name + "在吃" + food);
              },
              //箭头函数this不能使用，对象.属性
              eat2: food => console.log(person3.name + "在吃" + food),
              eat3(food) {
                  console.log(this.name + "在吃" + food);
              }
          }
  ```



* **对象扩展运算符**

  可以用`...object`代表某个对象所有的属性进行操作

  ```javascript
          // 1、拷贝对象（深拷贝）
          let p1 = { name: "Amy", age: 15 }
          let someone = { ...p1 }
          console.log(someone)  //{name: "Amy", age: 15}
  
          // 2、合并对象
          let age1 = { age: 15 }
          let name1 = { name: "Amy" }
          let p2 = {name:"zhangsan"}
          p2 = { ...age1, ...name1 } 
          console.log(p2) //{age: 15, name: "Amy"}
  ```



## 7. map 和 reduce函数

* **map函数**

  接收一个函数，将原数组中的所有元素用这个函数处理后放入新数组返回。

  ```javascript
          let arr = ['1', '20', '-5', '3'];
          arr = arr.map(item=> item*2); 
  		console.log(arr);	//[2, 40, -10, 6]
  ```



* **reduce函数**

  为数组中的每一个元素依次执行回调函数，不包括数组中被删除或从未被赋值的元素，

  `arr.reduce(callback,[initialValue])`

  `callback`函数可用四个参数

   	1、previousValue （上一次调用回调返回的值，或者是提供的初始值（initialValue））

   	2、currentValue （数组中当前被处理的元素）

   	3、index （当前元素在数组中的索引）

   	4、array （调用 reduce 的数组）

  ```javascript
          let arr =  [2, 40, -10, 6];
          let result = arr.reduce((a,b,index)=>{
              console.log("上一次处理后："+a);
              console.log("当前正在处理："+b);
              console.log(index);
              return a + b;
          },100);
          console.log(result)
  
  		//控制台打印结果
          上一次处理后：100
          当前正在处理：2
          0
          上一次处理后：102
          当前正在处理：40
          1
          上一次处理后：142
          当前正在处理：-10
  		2
          上一次处理后：132
          当前正在处理：6
          3
  ```



## 8. promise对象

当发送异步请求时，如果我们下一个异步请求需要用到上一个异步请求的结果，这个时候就会发生连续嵌套，非常混乱，而通过`promise`对象和`then`函数可以解决这一问题

原始的嵌套处理：

```javascript
        $.ajax({
            url:"mock/user.json",
            success:function(data){
                console.log("查到数据"+data.id)
                $.ajax({
                    url:`mock/user_corse_${data.id}.json`,
                    success:function(data){
                        console.log(data)
                    },
                    error:function(error){
                        console.log("出错。。"+error)
                    }
                });
            },
            error:function(error){
                console.log("出现异常"+error)
            }
        }   
              )
```

通过`promise`对象和`then`函数处理:

```javascript
        let p=new Promise((resolve,reject)=>{
            $.ajax({
                url:"mock/user.json",
                success:function(data){
                    console.log("查到数据"+data);
                    resolve(data);
                },
                error:function(err){
                    reject(err);
                }
            })
        });
        p.then(obj=>{
            $.ajax({
                url:`mock/user_corse_${obj.id}.json`,
                success:function(data){
                    console.log(data);
                }
            })
        })
```



## 9. 模块化

模块导入导出各种类型的变量，如字符串，数值，函数，类。

- 导出的函数声明与类声明必须要有名称（export default 命令另外考虑）。 
- 不仅能导出声明还能导出引用（例如函数）。
- export 命令可以出现在模块的任何位置，但必需处于模块顶层。
- import 命令会提升到整个模块的头部，首先执行。

```javascript
/*-----export [test.js]-----*/
let myName = "Tom";
let myAge = 20;
let myfn = function(){
    return "My name is" + myName + "! I'm '" + myAge + "years old."
}
let myClass =  class myClass {
    static a = "yeah!";
}
export { myName, myAge, myfn, myClass }
 
/*-----import [xxx.js]-----*/
import { myName, myAge, myfn, myClass } from "./test.js";
console.log(myfn());// My name is Tom! I'm 20 years old.
console.log(myAge);// 20
console.log(myName);// Tom
console.log(myClass.a );// yeah!
```

