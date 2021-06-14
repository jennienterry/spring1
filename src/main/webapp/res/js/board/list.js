const frmElem = document.querySelector('#frm')
const selectElem = frmElem.recordCnt;

selectElem.addEventListener('change', function(){
	frmElem.page.value = 1;
	frmElem.submit();
});

function moveToDetail(iboard){
	location.href='detail?iboard=' + iboard;
}