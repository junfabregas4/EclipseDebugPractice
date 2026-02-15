/**
 * Eclipse デバッグ練習用サンプル
 * 
 * 【練習の目的】
 * - ブレークポイントを設定して値を確認する
 * - if文の開始地点・終了地点で条件式のtrue/falseを見る
 * - Step Over (F6) で1行ずつ進めながら変数の変化を追う
 * 
 * 【ブレークポイントを置く場所】
 * コメントで「★BP」と書いてある行に設置してください
 */
public class Debugpractice {

    public static void main(String[] args) {

        System.out.println("=== デバッグ練習開始 ===");

        // --------------------------------------------------
        // 練習1: 単純なif文の条件確認
        // --------------------------------------------------
        int score = 75;
        String grade;

        if (score >= 80) {                          // ★BP① scoreの値を確認
            grade = "A";
        } else if (score >= 60) {
            grade = "B";
        } else {
            grade = "C";
        }
        System.out.println("練習1: score=" + score + ", grade=" + grade);  // ★BP② gradeの結果を確認

        // --------------------------------------------------
        // 練習2: 複数条件のif文（&&, ||）
        //   → Expressionsビューで条件式を評価する練習
        // --------------------------------------------------
        int age = 25;
        boolean isMember = true;
        int purchaseAmount = 3000;

        if (age >= 20 && isMember && purchaseAmount >= 5000) {  // ★BP③ 3つの変数を確認 & 条件式を評価
            System.out.println("練習2: プレミアム割引適用！");
        } else if (age >= 20 && isMember) {
            System.out.println("練習2: メンバー割引適用！");
        } else {
            System.out.println("練習2: 割引なし");
        }
        System.out.println("練習2: 分岐完了");       // ★BP④ どの分岐を通ったか確認

        // --------------------------------------------------
        // 練習3: ループ内のif文（変数の変化を追う）
        // --------------------------------------------------
        int total = 0;

        for (int i = 1; i <= 5; i++) {
            if (i % 2 == 0) {                       // ★BP⑤ iとtotalが毎回変わるのを観察
                total += i;
                System.out.println("練習3: i=" + i + " は偶数 → total=" + total);
            } else {
                System.out.println("練習3: i=" + i + " は奇数 → スキップ");
            }
        }
        System.out.println("練習3: 偶数の合計 = " + total);  // ★BP⑥ 最終的なtotalを確認

        // --------------------------------------------------
        // 練習4: オブジェクトの中身を確認する
        //   → Variablesビューでオブジェクトを展開する練習
        // --------------------------------------------------
        User user = new User("田中太郎", 30, true);
        int discount = calculateDiscount(user);      // ★BP⑦ Step Into(F5)でメソッドの中に入ってみよう

        if (discount > 0) {
            System.out.println("練習4: " + user.getName() + "さんの割引率 = " + discount + "%");
        } else {
            System.out.println("練習4: " + user.getName() + "さんは割引対象外");
        }

        // --------------------------------------------------
        // 練習5: 値を変更してみる（デバッグ中の値の書き換え）
        //   → Variablesビューで値を右クリック→Change Value
        // --------------------------------------------------
        String status = "pending";
        int retryCount = 3;

        if ("completed".equals(status)) {            // ★BP⑧ statusを"completed"に書き換えてみよう
            System.out.println("練習5: 処理完了");
        } else if (retryCount > 0) {
            System.out.println("練習5: リトライ中 (残り" + retryCount + "回)");
        } else {
            System.out.println("練習5: 処理失敗");
        }

        System.out.println("=== デバッグ練習終了 ===");
    }

    /**
     * 割引率を計算するメソッド
     * Step Into (F5) でこの中に入る練習用
     */
    private static int calculateDiscount(User user) {
        int discount = 0;                            // ★BP⑨ userの各フィールドを確認

        if (user.isVip() && user.getAge() >= 60) {
            discount = 20;  // VIPかつシニア
        } else if (user.isVip()) {
            discount = 10;  // VIPのみ
        } else if (user.getAge() >= 60) {
            discount = 5;   // シニアのみ
        }

        return discount;                             // ★BP⑩ discountの最終値を確認
    }
}

/**
 * ユーザー情報クラス
 * デバッグ時にオブジェクトの中身を展開して確認する練習用
 */
class User {
    private String name;
    private int age;
    private boolean vip;

    public User(String name, int age, boolean vip) {
        this.name = name;
        this.age = age;
        this.vip = vip;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isVip() {
        return vip;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + ", vip=" + vip + "}";
    }
}