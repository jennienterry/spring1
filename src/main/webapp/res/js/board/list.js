const frmElem = document.querySelector('#frm')
const selectElem = frmElem.recordCnt;

selectElem.addEventListener('change', function(){
console.log(selectElem.value);
});

function moveToDetail(iboard){
	location.href='detail?iboard=' + iboard;
}