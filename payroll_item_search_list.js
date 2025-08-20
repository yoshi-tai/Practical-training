// 詳細ボタン押下時
function details(){
	if (confirm("本当に詳細に遷移しますか？")) {
		console.log("遷移しました");
	} else {
		console.log("キャンセルしました");
	}
}


// 削除ボタン押下時
function deleteRecord(){
	if (confirm("本当に削除しますか？")) {
		console.log("遷移しました");
	} else {
		console.log("キャンセルしました");
	}
}


// 印刷処理
function print() {
  // 確認ダイアログを表示
  if (confirm("本当に印刷しますか？")) {

    // 一時的に印刷用のHTMLを作成
    let printContents = '<table border="1"><tr><th>名前</th><th>内容</th></tr>';

    // チェックされた行だけ抽出
    const rows = document.querySelectorAll('#dataTable tbody tr');
    rows.forEach(row => {
      const checkbox = row.querySelector('.print-check');
      if (checkbox && checkbox.checked) {
        const cells = row.querySelectorAll('td');
        printContents += `<tr><td>${cells[1].textContent}</td><td>${cells[2].textContent}</td></tr>`;
      }
    });

    printContents += '</table>';

    // 印刷用の新しいウィンドウを開く
    const printWindow = window.open('', '', 'width=800,height=600');
    printWindow.document.write('<html><head><title>印刷</title></head><body>');
    printWindow.document.write(printContents);
    printWindow.document.write('</body></html>');
    printWindow.document.close();
    printWindow.print();

    // コンソールにメッセージ
    console.log("印刷しました");

  } else {
    // 「キャンセル」が押されたとき
    console.log("キャンセルされました");
  }
}
//ラジオボタンの有無によって活性、非活性を行う 
   window.addEventListener("DOMContentLoaded", function () {

      const upRadio = document.getElementById('upRadio');
      const downRadio = document.getElementById('downRadio');
      const upYear = document.getElementById('upYear');
      const downLeftYear = document.getElementById('downLeftYear');
      const downLeftMonth = document.getElementById('downLeftMonth');
      const downRightMonth = document.getElementById('downRightMonth');

      const downRightYear = document.getElementById('downRightYear');
      const upCalender = document.getElementById('upCalender');
      const downLeftCalender = document.getElementById('downLeftCalender');
      const downRightCalender = document.getElementById('downRightCalender');

      function toggleDownRadio() {
        const isUp = upRadio.checked;
        const isDown = downRadio.checked;

        // upRadioのラジオが選ばれている → 下を無効化
        downLeftYear.disabled = isUp;
        downLeftCalender.disabled = isUp;
        downRightYear.disabled = isUp;
        downRightCalender.disabled = isUp;
        downLeftMonth.disabled = isUp;
        downRightMonth.disabled = isUp;

        // downRadioのラジオが選ばれている → 上を無効化
        upYear.disabled = isDown;
        upCalender.disabled = isDown;
      }

      // ラジオボタンが切り替わった時のイベント設定
      upRadio.addEventListener("change", toggleDownRadio);
      downRadio.addEventListener("change", toggleDownRadio);

      toggleDownRadio(); // ✅ 初期状態の有効・無効をセット
    });