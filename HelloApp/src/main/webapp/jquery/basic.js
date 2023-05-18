 // document.addEventListener('DOMContentLoaded', function(){
    //   let liTag = document.createElement('li');
    //   liTag.innerText = 'Cherry';
    //   liTag.append()
    //   console.log(liTag);
    //   document.querySelector('#list').append(liTag);
    // })
      //javascript객체 vs jQuery객체 (구분해서 사용해야함)
      $(document).ready(function(){

        // let elem = jQuery('<li />'); // let elem = $('<li />');
        // //elem.innerText -> 에러발생 jQuery에서는 사용 불가
        // elem.text('Cherry');
        // console.log(elem);
        $('#list').append($('<li />').text('Cherry'),
                          $('<li />').text('Durian'));
        
      })