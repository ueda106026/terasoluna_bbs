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
