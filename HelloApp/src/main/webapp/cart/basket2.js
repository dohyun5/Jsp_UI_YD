export let basket = {

  totalCount: 0, //전체 갯수 변수

  totalPrice: 0, //전체 합계액 변수

  //체크한 장바구니 상품 비우기

  delCheckedItem: function(){

      document.querySelectorAll("input[name=buy]:checked").forEach(function (item) {

          item.parentElement.parentElement.parentElement.remove();

      });

      //AJAX 서버 업데이트 전송

  

      //전송 처리 결과가 성공이면

      this.reCalc();

      this.updateUI();

  },

  //장바구니 전체 비우기

  delAllItem: function(){

      document.querySelectorAll('.row.data').forEach(function (item) {

          item.remove();

        });

        //AJAX 서버 업데이트 전송

      

        //전송 처리 결과가 성공이면

        this.totalCount = 0;

        this.totalPrice = 0;

        this.reCalc();

        this.updateUI();

  },

  //재계산

  reCalc: function(){

      this.totalCount = 0;

      this.totalPrice = 0;

      document.querySelectorAll(".p_num").forEach(function (item) {

          var count = parseInt(item.getAttribute('value'));9999

          this.totalCount += count;

          var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');

          this.totalPrice += count * price;

      }, this); // forEach 2번째 파라메터로 객체를 넘겨서 this 가 객체리터럴을 가리키도록 함. - thisArg

  },

  //화면 업데이트

  updateUI: function () {

      document.querySelector('#sum_p_num').textContent = '상품갯수: ' + this.totalCount.formatNumber() + '개';

      document.querySelector('#sum_p_price').textContent = '합계금액: ' + this.totalPrice.formatNumber() + '원';

  },

  //개별 수량 변경

  changePNum: function (pos) {

      var item = document.querySelector('input[name=p_num'+pos+']');

      var p_num = parseInt(item.getAttribute('value'));

      var newval = event.target.classList.contains('up') ? p_num+1 : event.target.classList.contains('down') ? 

p_num-1 : event.target.value;

      

      if (parseInt(newval) < 1 || parseInt(newval) > 99) { return false; }



      item.setAttribute('value', newval);

      item.value = newval;



      var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');

      item.parentElement.parentElement.nextElementSibling.textContent = (newval * price).formatNumber()+"원";

      //AJAX 업데이트 전송



      //전송 처리 결과가 성공이면    

      this.reCalc();

      this.updateUI();

  },

  //상품 삭제

  delItem: function () {

      event.target.parentElement.parentElement.parentElement.remove();

  },
  cartList: function () {
    cartItems.forEach((item, idx) => {
        let template = document.querySelector('#template>div.row.data').cloneNode(true);
        template.querySelector('.img>img').setAttribute('src', '../img/' + item.image)
        template.querySelector('.pname>span').textContent = item.productNm
        template.querySelector('.basketprice>input').value = item.price
        template.querySelector('.basketprice').childNodes[2].textContent = item.price.formatNumber() + "원"
        template.querySelector('.updown>input').value = item.qty
        template.querySelector('.updown>input').setAttribute('value', item.qty)
        template.querySelector('.updown>input').setAttribute('id', 'p_num' + (idx + 1));
        template.querySelector('.sum').textContent = (item.price * item.qty).formatNumber() + "원"

        document.querySelector('#basket').append(template)
    })
}
  

}


var cartItems = [{
  no: 1,
  productNm: '이것이 민트다.',
  qty: 2,
  price: 12000,
  image: 'item1.PNG'
},
{
  no: 2,
  productNm: '와 아이스크림.',
  qty: 1,
  price: 22000,
  image: 'item2.PNG'
},
{
  no: 3,
  productNm: '모나카 케익.',
  qty: 1,
  price: 13600,
  image: 'item3.PNG'
}
]

Number.prototype.formatNumber = function () {
  if (this == 0) return 0;
  let regex = /(^[+-]?\d+)(\d{3})/;
  let nstr = (this + '');
  while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
  return nstr;
};