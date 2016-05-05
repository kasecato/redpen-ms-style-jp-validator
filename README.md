# Microsoft Japanese Style Guide for RedPen
[![Circle CI](https://circleci.com/gh/k--kato/redpen-ms-style-jp-validator.svg?style=svg)](https://circleci.com/gh/k--kato/redpen-ms-style-jp-validator)
[![Coverage Status](https://coveralls.io/repos/github/k--kato/redpen-ms-style-jp-validator/badge.svg?branch=master)](https://coveralls.io/github/k--kato/redpen-ms-style-jp-validator?branch=master)
[![License: Apache](http://img.shields.io/:license-apache-blue.svg?style=flat-square)](http://www.apache.org/licenses/LICENSE-2.0.html)

Microsoft Style Guides are collections of rules that define language and style conventions for specific languages. These rules usually include general localization guidelines, information on language style and usage in technical publications, and information on market-specific data formats.

## Install

You can use it by copying the `ms-style-jp-validator-0.1.0.jar` file to the RedPen library directory ($REDPEN_HOME/lib).

### Mac OS X

```bash
/usr/local/Cellar/redpen/1.4.4/libexec/lib/
```

### Windows

```bash
c:\Program Files\redpen-cli-1.0\lib\
```

## Usage

redpen-conf.xml
```XML
<redpen-conf lang="ja" type="zenkaku2">
  <validators>
    <validator name="MSStyleJP" />
  </validators>
</redpen-conf>
```

## Language Specific Conventions


### Hiragana

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
                                                  | 昭和 61 年 7 月 1 日 内閣告示第 1 号「現代仮名遣い」   | N/A


### Katakana

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
3 ケ月, 3 ヶ月, 3 カ月, 3 ヵ月                      | 3 か月                                            | ✅
5 ケ, 5 コ                                        | 5 個                                              | ✅


### Long vowel

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
                                                  | 平成 3 年 6 月 28 日 内閣告示第 2 号「外来語 表記」   | N/A


### Kanji

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
                                                  | 平成 22 年 11 月 30 日 内閣告示第 2 号「常用漢字表」<br/>昭和 48 年 6 月 18 日 内閣告示第 2 号「送り仮名 付け方」 | N/A

### English letters

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
ＮＯＴＥ                                           | NOTE                                             | ✅


### Spaces

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
第3章                                              | 第 3 章                                           | ✅
ボタンをクリックして 、 閉じます 。                    | ボタンをクリックして、閉じます。                      | ✅
45 °                                              | 45°                                               | ✅
列 A ( タイトル )                                   | 列 A (タイトル)                                    | ✅
[ 新規 ] をクリックします。                           | [新規] をクリックします。                           | ✅
「 test 」と入力します。                             | 「test」と入力します。                              | ✅
3 / 14                                            | 3/14                                              | ✅
更新しますか ?                                      | 更新しますか?                                      | ✅
更新しますか ？                                     | 更新しますか？                                      | ✅
警告 !                                             | 警告!                                             | ✅
警告 ！                                            | 警告！                                            | ✅
フォント :                                          | フォント:                                         | ✅
フォント ：                                         | フォント：                                        | ✅
その他 …                                            | その他…                                          | ✅
保存(S)                                             | 保存 (S)                                         | N/A
保存 (S)                                            | 保存(S)                                          | ✅
変換 キー                                           | 変換キー                                          | N/A
ページレイアウト                                     | ページ レイアウト                                  | N/A
「第 2 章コントロール」を参照してください。              | 「第 2 章 コントロール」を参照してください。          | N/A
3kg                                                | 3 kg                                             | N/A
50 %                                               | 50%                                              | ✅
10 mm                                              | 10mm                                             | ✅
10/13(ページ)                                       | 10/13 (ページ)                                    | ✅
保存しますか?Excelを使用して編集する場合                | 保存しますか? Excelを使用して編集する場合             | ✅
「2.1Active Directory」を参照してください。           | 「2.1 Active Directory」を参照してください。         | N/A


### Date

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
-                                                 | -                                                | N/A


### Time

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------

### Days

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
-                                                 | -                                                | N/A

### Months

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
-                                                 | -                                                | N/A


### Numbers

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
ひとつ                                             | 1 つ                                            | N/A
一回                                               | 1 回                                            | ✅
一画面                                             | 1 画面                                           | ✅
(雑誌を) 一部                                       | 1 部                                            | ✅
一月                                               | 1 月                                            | ✅
二乗                                               | 2 乗                                            | ✅
三次元                                             | 3 次元                                           | ✅
四分位数                                           | 4 分位数                                         | ✅
二十行                                             | 20 行                                           | ✅
八十桁                                             | 80 桁                                           | ✅
百五十語                                           | 150 語                                           | ✅
一時間十五分                                        | 1 時間 15 分                                     | ✅
二進法                                             | 2 進法                                           | ✅
二百五十六色                                        | 256 色                                           | ✅
もう 1 度                                          | もう一度                                         | N/A
1 部                                              | (画面 ) 一部                                      | N/A
2 項分布                                           | 二項分布                                         | N/A
最小 2 乗法                                        | 最小二乗法                                       | N/A
100 分位数                                         | 百分位数                                         | N/A
100 分率                                           | 百分率                                          | N/A
3 角形                                             | 三角形                                          | N/A
4 捨 5 入                                          | 四捨五入                                         | N/A
1 時的に                                           | 一時的に                                         | N/A

### Phone Numbers

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
-                                                 | -                                                | N/A

### Addresses

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
-                                                 | -                                                | N/A


### Currency

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
-                                                 | -                                                | N/A

### Digit Groups

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
-                                                 | -                                                | N/A

### Measurement Units

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
キロメートル                                        | km                                              | ✅
メートル                                            | m                                               | ✅
デシメートル                                        | dm                                              | ✅
センチメートル                                      | cm                                              | ✅
ミリメートル，ミリ                                   | mm                                              | ✅
ヘクトリットル                                       | hL                                              | ✅
リットル                                            | L                                               | ✅
デシリットル                                         | dL                                              | ✅
センチリットル                                       | cL                                              | ✅
ミリリットル                                        | mL                                              | ✅
トン                                               | t                                               | ✅
キログラム                                          | kg                                              | ✅
lb                                                | ポンド                                           | ✅
グラム                                             | g                                               | ✅
デシグラム                                          | dg                                              | ✅
センチグラム                                        | cg                                              | ✅
ミリグラム                                          | mg                                              | ✅
in                                                | インチ                                           | ✅
ft                                                | フィート                                         | ✅
mi                                                | マイル                                           | ✅
gal                                               | ガロン                                           | ✅
テラバイト                                          | TB                                              | ✅
ギガバイト                                          | GB                                              | ✅
メガバイト                                          | MB                                              | ✅
キロバイト                                          | KB                                              | ✅
B                                                 | バイト                                           | ✅
b                                                 | ビット                                           | ✅
ビット/秒                                          | bps                                             | ✅
ギガヘルツ                                          | GHz                                             | ✅
メガヘルツ                                          | MHz                                             | ✅
キロヘルツ                                          | kHz                                             | ✅
ヘルツ                                             | Hz                                              | ✅
ドット                                             | dot                                             | ✅
ドット/インチ                                       | dpi                                             | ✅
sec                                               | 秒                                               | ✅
ms                                                | ミリ秒                                            | ✅

#### Units without a space

Incorrect (-)                                     | Correct (+)                                      | Support
--------------------------------------------------|--------------------------------------------------|--------
45 度                                             | 50°                                              | ✅
50 パーセント                                      | 50%                                              | ✅
50 %                                              | 50%                                              | ✅
50 ％                                             | 50%                                              | ✅




# References

1. Microsoft, "Japanese Style Guide", https://www.microsoft.com/Language/en-US/StyleGuides.aspx
1. RedPen, "Extending RedPen", http://redpen.cc/docs/1.5/index.html#extending
