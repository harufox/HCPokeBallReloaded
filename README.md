# HCPokeBallReloaded

![Java](https://img.shields.io/badge/Java-17+-blue.svg)
![PaperMC](https://img.shields.io/badge/Paper-1.21.5-yellow.svg)
![Maven](https://img.shields.io/badge/build-Maven-green.svg)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)

Minecraft Paperサーバー向けプラグイン。特定のモブを「ポケボール」に捕獲・保存し、あとで呼び出せるようにします。

---

## 📦 インストール手順

1. [Releases](https://github.com/harufox/HCPokeBallReloaded/releases) から最新の `HCPokeBallReloaded-x.x.x.jar` をダウンロード
2. `plugins` フォルダに jar ファイルを配置
3. 対応バージョンの Paper（1.21.5）サーバーを起動

捕獲対象のMOBはconfig.ymlに記載されています。
ここから記載を削除することで捕獲対象から外すことができます。

---

## 🎮 PokeBallの使い方

### クラフト方法

PokeBall はクラフトで入手可能です。

```text

クラフトの良い感じの画像 

```

### 捕獲の流れ

1. ポケボールをペットに向かって投げてください。
2. ボールが手元に戻ってくると成功です。
3. 捕獲済みのポケボールを投げると、捕獲時のペットがそのまま出てきます。

---

## 🛠 開発者向け情報

### ✅ 新しい捕獲対象の追加方法

1. `hcpokeball.data` パッケージに対象モブのデータクラスを作成します。  
   - `EntityData` インターフェースを実装し、対象モブのプロパティをJSONとして保存・復元できるようにします。
   - 例: `WolfData`, `CatData` など

2. `hcpokeball.handler` パッケージにハンドラークラスを追加します。  
   - `EntityCaptureHandler<T>` を実装し、対応モブの serialize/deserialize を定義します。
   - 例: `WolfCaptureHandler`, `CatCaptureHandler` など

3. `CaptureHandlerRegistry` に新しいハンドラーを登録します：

```java
CaptureHandlerRegistry.register(EntityType.WOLF, new WolfCaptureHandler());
```

これにより、ポケボールで新しいモブを捕獲・保存・復元できるようになります。

必要に応じて`hcpokeball.util.mapper`にマッパークラスを作成してください。

- 例: オオカミの姿マッパーである `WolfVariantMapper` など

---

## 📝 ライセンス

このプロジェクトは [MIT License](LICENSE) の下で提供されています。
