# Eclipse デバッグ練習ガイド

## 1. Eclipseでプロジェクトを作成する

1. **File → New → Java Project**
2. Project name: `DebugPractice` と入力
3. **Finish** をクリック

## 2. ソースファイルをプロジェクトに追加する

### 方法A: コピー＆ペースト
1. 左側の Package Explorer で `src` フォルダを右クリック
2. **New → Class** を選択
3. Name に `DebugPractice` と入力して **Finish**
4. 自動生成されたコードを全選択（Ctrl+A）して削除
5. ダウンロードした `DebugPractice.java` の中身を貼り付けて保存（Ctrl+S）

### 方法B: ファイルをドラッグ＆ドロップ
1. ダウンロードした `DebugPractice.java` を Eclipse の `src` フォルダにドラッグ
2. **Copy files** を選択して OK

## 3. ブレークポイントを設定する

コード内の `★BP①` 〜 `★BP⑩` のコメントがある行に設定します。

- 該当する行の **左端（行番号の左の灰色エリア）をダブルクリック**
- 青い丸 ● が表示されればOK

### まずは以下の3箇所だけ設定して始めてみましょう
- **★BP①**（25行目）: `if (score >= 80)` の行
- **★BP②**（32行目）: `System.out.println("練習1:...` の行
- **★BP③**（41行目）: `if (age >= 20 && isMember ...` の行

## 4. デバッグ実行する

1. コードエディタ上で **右クリック → Debug As → Java Application**
   - または上部ツールバーの **虫アイコン 🪲** をクリック
2. 「Debug パースペクティブに切り替えますか？」→ **Switch** を選択

## 5. デバッグ中の操作方法

### 値の確認方法（3つ）

| 方法 | やり方 | 用途 |
|------|--------|------|
| **Variables ビュー** | 画面右上のパネルに自動表示される | 全変数を一覧で見たい時 |
| **マウスホバー** | コード上の変数にカーソルを乗せる | 特定の変数をサッと見たい時 |
| **Expressions ビュー** | 後述の手順で式を追加 | **条件式のtrue/false**を見たい時 |

### Expressionsビューで条件式を評価する（★重要）

**これが「if文の条件がtrueかfalseか見る」方法です！**

1. **Window → Show View → Expressions** でビューを開く
2. Expressions ビュー内の空欄をクリック
3. 確認したい式を入力（例）:
   - `score >= 80` → true か false が表示される
   - `age >= 20 && isMember` → true か false が表示される
   - `i % 2 == 0` → ループ内で毎回結果が変わるのを観察できる

### ステップ実行キー（Eclipse版）

| キー | 名前 | 意味 |
|------|------|------|
| **F6** | Step Over | 今の行を実行して次の行へ進む |
| **F5** | Step Into | メソッドの中に入る |
| **F7** | Step Return | 今のメソッドから出る |
| **F8** | Resume | 次のブレークポイントまで一気に進む |
| **Ctrl+Shift+I** | Inspect | 選択した式を評価（コード上で式を選択してから押す） |

> ⚠️ **IntelliJとキーが違います！**  
> IntelliJ の F8（Step Over）→ Eclipse では **F6**  
> IntelliJ の F7（Step Into）→ Eclipse では **F5**

## 6. 練習メニュー

### 練習1: 基本の値確認
1. ★BP① で止まったら Variables ビューで `score = 75` を確認
2. Expressions に `score >= 80` と入力 → **false** になることを確認
3. **F6** を数回押して進み、`grade = "B"` が代入される瞬間を見る
4. ★BP② で `grade` の値が `"B"` になっていることを確認

### 練習2: 複合条件の評価
1. ★BP③ で止まったら Expressions に以下を追加:
   - `age >= 20 && isMember && purchaseAmount >= 5000` → **false**
   - `age >= 20 && isMember` → **true**
2. **F6** で進むと2番目の `else if` に入ることを確認

### 練習3: ループでの変化観察
1. ★BP⑤ を設定してデバッグ実行
2. **F8（Resume）** を押すたびにブレークポイントで止まる
3. Variables ビューで `i` と `total` の値が毎回変わるのを観察

### 練習4: Step Into でメソッドに入る
1. ★BP⑦ の行で **F5（Step Into）** を押す
2. `calculateDiscount` メソッドの中に入る
3. Variables ビューで `user` を **▶ 展開** して中身（name, age, vip）を確認

### 練習5: デバッグ中に値を書き換える
1. ★BP⑧ で止まったら Variables ビューで `status` を右クリック
2. **Change Value** を選択
3. `"completed"` に書き換えて OK
4. **F6** で進むと、本来通らないはずの `if ("completed".equals(status))` に入る！

## 7. よくあるトラブル

| 症状 | 原因と対策 |
|------|-----------|
| ブレークポイントで止まらない | Run ではなく **Debug As** で実行しているか確認 |
| Variables が空 | Debug パースペクティブになっているか確認（Window → Perspective → Open → Debug） |
| ソースが見つからない | プロジェクトのビルドパスにソースが含まれているか確認 |
| F6 を押しても動かない | Debug ビューで対象のスレッドが選択されているか確認 |