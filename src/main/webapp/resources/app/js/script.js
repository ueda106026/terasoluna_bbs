const submitButton = document.getElementById('submitButton'); // 投稿ボタンのIDを取得

// 投稿を実行
submitButton.addEventListener("click", (event) => {
    event.preventDefault(); // デフォルトの送信動作をキャンセルする
    const inputName = document.getElementById('inputName').value; // 名前フォームの値を取得
    const inputMessage = document.getElementById('inputMessage').value; // メッセージフォームの値を取得
    const errorMassageToName = document.getElementById('errorMassageToName'); // 名前のエラーメッセージの出力先を取得
    const errorMassageToMessage = document.getElementById('errorMassageToMessage'); // メッセージのエラーメッセージの出力先を取得
    let outputMassageToName = ''; // 名前のエラーメッセージを空白に設定
    errorMassageToName.innerHTML = outputMassageToName; // ビューに出力
    let outputMassageToMessage = ''; // メッセージのエラーメッセージを空白に設定
    errorMassageToMessage.innerHTML = outputMassageToMessage; // ビューに出力

    // 名前の入力チェック
    if (inputName === '' || inputName === null) {
        outputMassageToName = '名前を入力してください。';
        errorMassageToName.innerHTML = outputMassageToName; // ビューに出力
        return;
    }
    if (inputName.length > 32) {
        outputMassageToName = '名前は32文字以内で入力してください。';
        errorMassageToName.innerHTML = outputMassageToName; // ビューに出力
        return;
    }
    // メッセージの入力チェック
    if (inputMessage === '' || inputMessage === null) {
        outputMassageToMessage = 'メッセージが未入力です。';
        errorMassageToMessage.innerHTML = outputMassageToMessage; // ビューに出力
        return;
    }
    if (inputMessage.length > 1000) {
        outputMassageToMessage = 'メッセージは1000文字以内で入力してください。';
        errorMassageToMessage.innerHTML = outputMassageToMessage; // ビューに出力
        return;
    }

    //処理を実行
    document.inputForm.submit();
});

// 管理人名の非表示・フォームの表示
$('#formOpen').on('click', function(event) {
    event.preventDefault();
    const errorMassageToAdministrator = document.getElementById('errorMassageToAdministrator'); // 管理人名のエラーメッセージの出力先を取得
    let outputMassageToAdministrator = ''; // 名前のエラーメッセージを空白に設定
    errorMassageToAdministrator.innerHTML = outputMassageToAdministrator; // ビューに出力
    $('.administrator').hide();
    $('.administratorInput').show();
});

// 管理人名の表示・フォームの非表示
$('#formHide').on('click', function(event) {
    event.preventDefault();
	const adminValue = document.getElementById('formOpen').innerText;
	$('#administratorForm').val(adminValue);
    $('.administrator').show();
    $('.administratorInput').hide();
});

// 管理人名の変更処理
$('#administratorSubmit').on('click', function(event) {
    event.preventDefault();
    const administratorForm = document.getElementById('administratorForm').value; // 管理人名フォームの値を取得
    const errorMassageToAdministrator = document.getElementById('errorMassageToAdministrator'); // 管理人名のエラーメッセージの出力先を取得
    let outputMassageToAdministrator = ''; // 名前のエラーメッセージを空白に設定
    errorMassageToAdministrator.innerHTML = outputMassageToAdministrator; // ビューに出力

    // 名前の入力チェック
    if (administratorForm === '' || administratorForm === null) {
        outputMassageToAdministrator = '管理人名を入力してください。';
        errorMassageToAdministrator.innerHTML = outputMassageToAdministrator; // ビューに出力
        return;
    }
    if (administratorForm.length > 32) {
        outputMassageToAdministrator = '名前は32文字以内で入力してください。';
        errorMassageToAdministrator.innerHTML = outputMassageToAdministrator; // ビューに出力
        return;
    }
	
	const CSRFToken = document.getElementById('CSRFToken').value; // CSRFトークンの値を取得
	console.log(CSRFToken);

    // 処理を実行
	/*
	$.ajax({
	    type : 'POST',           // HTTP通信のタイプ(get, post, put など)
	    url : 'http://localhost:8080/bbs/administrator_update',           // リクエストを送信する先のURL
	    async : true,            // 非同期化の設定 (default : true)
	    headers : {              // Http header
	      "Content-Type" : "application/json", // 送信するデータの型
	    },
	    dataType : 'text',       // サーバから返されるデータの型(html, xml, json, text など)
	    data : JSON.stringify({  // 送信するデータの型(Object , String, Array)
	      "administrator" : "test"
	    }),
	    success : function(result) { // 成功時に実行されるコールバック関数
	        console.log("成功しました");
	    },
	    error : function(request, status, error) { // 通信に失敗したときに呼ばれるコールバック関数。
	        console.log("失敗しました")
	    }
	})
	*/
	
	$.ajax({
	  type: "POST",
	  url: "http://localhost:8080/bbs/administrator_update",
	  dataType: "json",
	  contentType: "application/json",  // JSONで送る場合に必須
	  data: JSON.stringify({ 
	    "administrator": administratorForm
	  }),
	  headers: {
	    'X-CSRF-Token' : $('#CSRFToken').val()
	  },
	})
	.done(function () {
		window.location.reload();
	})
	.fail(function () {
	  window.location.href('../../WEB-INF/views/error_exception.html')
	});
});
