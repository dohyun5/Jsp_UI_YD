var APP_LOG_LIFECYCLE_EVENT = false;
    let store = new Vue({
      el: '#app', //vue만들어서 보여줄 위치 
      data: {
        sitename: 'Vue.js 애완용품 샵',
        // product: {
        //   id: 1001,
        //   title: '고양이 사료, 25파운드',
        //   description: "당신의 고양이를 위한<em><strong>거부할 수 없는</strong></em>," + "유기농 사료입니다.",
        //   price: 2000,
        //   image: 'images/product-fullsize.png',
        //   availableInventory: 5
        // },
        products:[],
        cart:[],
        showProduct: true,
        order: {
          firstName:'',
          lastName:'',
          address:'',
          city:'',
          zip:'',
          state:'',
          method:'자택 주소',
          bisiness:'직장 주소',
          home:'자택 주소',
          gift:'선물로 보내기',
          sandGift: '선물로 보내기',
          dontSendGift:'선물로 보내지 않기'
        },
        states:{
          AL:'알라바마',
          AK:'알래스카',
          AR:'애리조나',
          CA:'캘리포니아',
          NV:'네바다'
        }
      },methods:{
        addToCart: function(aproduct){
          this.cart.push(aproduct.id);
        },
        showCheckout(){
          this.showProduct = this.showProduct ? false : true;
        },
        cartCount(id){
          let count = 0;
          for(let i =0;i<this.cart.length;i++){
            if(this.cart[i] == id){
              count++;
            }
          }
          return count;
        },
        canAddToCart: function(aproduct){
          //return false;
          return aproduct.availableInventory > this.cartCount(aproduct.id);
        },
        checkRating(n, aproduct){
          return aproduct.rating >= n;
        }
      },
      computed:{
        cartItemCount: function(){
          return this.cart.length;
        },
        sortedProducts:function(){
          //{id, title, rating, price, description}
          return this.products.sort(function(p1,p2){
            //오름차순 : -1
            //내림차순 : 1
            //return p1.price - p2.price;
            // 가 < 나
            if(p1.title < p2.title){
              return -1;
            } else{ 
              return 1;
            }
            
          });
        }
      },
      filters: {
        formatPrice: function(price){
          //99999: 999.99  2000.00 -> 2,000.00
          if(!parseInt(price)){return "";}
          if(price>99999){
            var priceString = (price/100).toFixed(2);//소수로 변환
            console.log(priceString);
            var priceArray = priceString.split("").reverse();//역으로 출력
            console.log(priceArray);
            var index = 3;
            while(priceArray.length > index+3){
             priceArray.splice(index + 3,0,","); // 3자리 마다 ','추가하겠단 의미 
             index += 4; 
            }
            return "$" + priceArray.reverse().join(""); //형식에 맞게 반환
          }else {
            return "$" + (price/100).toFixed(2); //1000미만일때 값 반환
          }
        }
      },
      brforeCreate: function () {
        if (APP_LOG_LIFECYCLE_EVENT) {
          console.log('beforeCreate hook')
        }
      },
      created: function () {
        if (APP_LOG_LIFECYCLE_EVENT) {
          console.log('Created hook')
        // fetch('data.json')
        //   .then(resolve => resolve.json())
        //   .then(result => {
        //     store.sitename = result.sitename
        //   })
        //   .catch(err => console.log);
        }
        axios.get('./products.json')
        .then(result => {
          console.log(result)
          this.products = result.data.products;
        })
      
      },
      beforeMount: function () {
        if (APP_LOG_LIFECYCLE_EVENT) {
          console.log('beforeMount hook')
        }
      },
      mounted: function () {
        if (APP_LOG_LIFECYCLE_EVENT) {
          console.log('mounted hook')
        }
      },
      beforeUpdate: function () {
        if (APP_LOG_LIFECYCLE_EVENT) {
          console.log('beforeUpdate hook')
        }
      },
      updated: function () {
        if (APP_LOG_LIFECYCLE_EVENT) {
          console.log('updated hook')
          //alert('updated')
        }
      },
      beforeDestroy: function () {
        if (APP_LOG_LIFECYCLE_EVENT) {
          console.log('beforeDestroy hook')
        }
      },
      destroyed: function () {
        if (APP_LOG_LIFECYCLE_EVENT) {
          console.log('destroyed hook')
        }
      }
    });