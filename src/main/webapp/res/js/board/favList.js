const listElem = document.querySelector('#list');

function getListAjax(){
    fetch('fav')
        .then(res => res.json()) // => 는 return 적은 것과 같은 효과가 난다.
        .then(myJson => {
            console.log(myJson);
            makeView(myJson);
        });
}
// 위와 같은데 모든 브라우저 가능, 위는 최신버전만 가능
// fetch('fav')
//     .then(function(res){
//         return res.json();
// })
//     .then(function(myJson) {
//         console.log(myJson);
//     });

function makeView(data){
    listElem.innerHTML=''; //값 있으면 지워줘

    const table = document.createElement('table');
    listElem.append(table);

    table.innerHTML=`
     <tr>
        <th>번호</th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>작성일시</th>
    </tr>`;

    data.forEach(item => {
        const tr = document.createElement('tr');
        table.append(tr);

        tr.classList.add('record');
        tr.addEventListener('click',()=> {
            moveToDetail(item.iboard);
        });

        let imgSrc = '/res/img/noprofile.jpg';
        if(item.profileImg != null){
            imgSrc = `/img/${item.iuser}/${item.profileImg}`;
        }
        tr.innerHTML=`
         <td>${item.iboard}</td>
         <td>${item.title}</td>
         <td>${item.writerNm} <img src="${imgSrc}" class="profileImg"></td> 
         <td>${item.regdt}</td> `;
    });
}
function moveToDetail (iboard){
    location.href = '/board/detail?iboard=' + iboard;
}

getListAjax();
