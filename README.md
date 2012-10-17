## QiitaDashとは
[Dash](https://itunes.apple.com/us/app/dash-docs-snippets/id458034879?mt=12)というMac向けのアプリケーションがあり、プログラミングにおける「定型文」「よく使う表現」を簡単に入力する事ができます。
QiitaDashは、[Qiita](http://qiita.com)上で公開されている「定型文」をDashの管理下に追加するプログラムです。検索条件を指定するだけで、Qiita上の「Dash」タグがついた記事達からソースコードを抜き出し、完全自動でDashに登録を行う事が出来ます

## QiitaDashの使い方
[.jarファイル](https://github.com/downloads/awakia/qiita_dash/QiitaDash-0.1.jar)をダウンロードし，コマンドラインから
> java -jar QiitaDash-0.1.jar のように実行します．
現在以下のコマンドライン引数に対応しています．
*-j* 日本語キーボードを利用している事をQiitaDashに教えます．日本語キーボードをお使いの場合はこのオプションを必ず指定してください．
*-n itemNumberLimit* 取得するスクリプトの最大値を指定します
*-t additionalTag* 検索結果のフィルタリングに利用するタグ名を指定します


## Qiitaへの投稿の仕方

あなたが[Dash](https://itunes.apple.com/jp/app/dash/id458034879?mt=12&ign-mpt=uo%3D4)上で普段利用しているコードスニペットを他の人と共有できるように，[Qiita](http://qiita.com/)へ投稿してみましょう．

1. http://qiita.com/ へアクセスし，**ノウハウ・Tipsを投稿する**をクリック
2. 自分の利用するコードスニペットを含む記事を記入してください．その際，コードは\`\`\`で囲んで記入してください．また，\`\`\`の直後の一行は，スニペットのタイトルとして利用されます．qiita_dashは，記事中の最初にあるコードを抽出し，Dashのテンプレートとして利用します．
3. **投稿する**をクリックする前に，**Dash**タグを追加するのを忘れないでください．他にも関係するタグを追加した場合，それらのタグはDashのタグとして利用されます．


投稿画面は以下のようになるでしょう．

![投稿画面例](https://raw.github.com/awakia/qiita_dash/master/QiitaDash/doc/how-to-post-your-snippet.png)


## 注意
* 日本語配列のキーボードをお使いの方は-jオプションを必ず含めてください．
* スポットライトのショートカットはCtrl+Shift+Spaceでお願いします・・・

このソフトウェアはオープンソースです．使うときはコードからいじって使ってください．
Pull Request絶賛募集中！