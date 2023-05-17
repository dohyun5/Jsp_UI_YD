//{name : "Hong",age:20} => object
const obj = {
  days: ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'],
  makeThead: function () {
    let thd = document.createElement('thead');
    let tr = document.createElement('tr')
    for (let day of this.days) {
      console.log(day)
      let th = document.createElement('th')
      th.innerText = day;
      tr.append(th);
    }
    thd.append(tr);
    return thd;
  },
  makeTbody(month) {
    let tbd = document.createElement('tbody')
    let tr = document.createElement('tr')
    for (let i = 0; i < this.getFirstDay(month); i++) {
      tr.append(document.createElement('td'));
    }
    for (let i = 1; i <= this.getLastDate(month); i++) {
      let td = document.createElement('td')
      td.innerText = i;
      tr.append(td);
      if ((i+this.getFirstDay(month)) % 7 == 0) {
        tbd.append(tr);
        tr = document.createElement('tr');
      }
    }
    tbd.append(tr);
    console.log(tbd);
    return tbd;
  },
  makeTable(month,elem) {
    let tbl = document.createElement('table');
    tbl.append(this.makeThead());
    tbl.append(this.makeTbody(month));
    //return tbl;
    elem.append(tbl);
  },
  getFirstDay(month = 5) {
    if (month == 5) {
      return 1;
    } else if (month == 6) {
      return 4;
    }
  },
  getLastDate(month = 5) {
    if (month == 5) {
      return 31;
    } else if (month == 6) {
      return 30;
    }
  }
}

// // #show 에 하위요소
// show.append(obj.makeTable(6));
obj.makeTable(5,show);