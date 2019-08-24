# 開発環境の構築について

## ソースコードを取得

- [github repository](https://github.com/yoshiki-0428/BizToihttps://github.com/yoshiki-0428/BizToi) にアクセス

> Privateリポジトリなので、ユーザ名をオーナーに共有する必要がある
> https://techacademy.jp/magazine/6235

- gitでソースコードをCloneする (https://github.com/yoshiki-0428/BizToi.git)

> git、もしくはSourceTreeをインストール

- ディレクトリを確認し、`backend` と `front` があること確認

## /backend の説明

- サーバサイド言語の`Kotlin`で(フレームワークはSpringBoot)実装されている

### 役割

- ユーザの認証, 管理: AWS Cognito (クラウドサービス)
- DBアクセス: MySQL
- API URL: http://localhost:8000/api/

> [localhostについて](https://qiita.com/yusaku_/items/bc7dc4cd654247d2a0aa)

## /front の説明

- JavaScript のフレームワークである`Vue.js`で実装

### 役割

- ユーザに表示される画面
- Backendと通信しユーザのもつ情報を加工し、適切に表示をする
- URLのルーティングを担当
> イメージ: User <-> Front(Vue) <-> Backend(Kotlin) <-> DB(MySQL)

## 環境構築手順 (frontの開発を行う場合)

- ソースコードを取得したディレクトリを対象に`Atom` or `VSCode`を起動する 
> IDE(統合開発環境) はいろんな種類があるからいろいろ試してみて！windowsだったら VSCodeがおすすめ

- コンテナ仮想環境構築ツールのDockerをインストール
> Windowsの場合は↓を参考に
> https://ops.jig-saw.com/techblog/docker-for-windows-install/
> https://qiita.com/fkooo/items/d2fddef9091b906675ca

- cd biztoi
- docker-compose up --build -d (`docker-compose.yml`ファイルが存在する場所で行う)
- node, npm をインストール
> [windowsの場合のインストール手順](https://qiita.com/taiponrock/items/9001ae194571feb63a5e)

- node --version でバージョン確認(v12.n.n ならOK)
- npm install -g yarn 
> [npm install のグローバルとは](https://qiita.com/kijitoraneko/items/175ef29d45d155b3f405)

- yarn
> [yarn とは](https://qiita.com/jigengineer/items/c75ca9b8f0e9ce462e99)

- yarn serve
> http://localhost:3000/ にアクセスして画面が表示されるか確認

