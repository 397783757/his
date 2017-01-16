package com.his.model;


import com.google.gson.Gson;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Arrays;

/**
 * Created by Administrator on 2016-09-26.
 */
public class Drug { Gson gson= new Gson();
    private Integer id;
    private String code;
    private String name;
    private Boolean prin;
    private String ratio;
    private String source;
    private String big_unit;
    private String big_unit_name;
    private String allergic_history;
    private String bar_code;
    private String pinyin_code;
    private String wubi_code;
    private String custom_code;
    private String unit_content;
    private String content_unit;
    private String content_unit_name;
    private String drug_type;
    private String drug_spec;
    private String small_unit;
    private String small_unit_name;
    private String note;
    private String big_price;
    private String small_price;
    private String medicine_type;
    private String bbuy_price;
    private String sbuy_price;
    private String drug_classification;
    private String charge_classification;
    private String c_charge_classification;

    private String use_level;
    private String times;
    private String days;
    private String manufacturer;
    private String address;
    private String license_number;
    private String brand;
    private String Latin;
    private String usages;
    private String standard;
    private String drug_category;

    //private String belong;
    private String minchen;
    private String star_date;
    private String organization_code;
    private Boolean social_security;
    private Boolean drip;
    private Boolean stop;
    private Boolean internal;
    private Boolean surgery;
    private Boolean gynaecology;
    private Boolean recovered;
    private Boolean new_medicine;
    private Boolean recent_medicine;
    private Boolean stock_medicine;
    private String new_medicine_text;
    private String recent_medicine_text;
    private String stock_medicine_text;
    private String drug_content;



    //五笔字库
    private static String[] WB = new String[] {
            "A鞴鞲鞣鞫鞯鞔鞒鞑鞅靼銎跫綦翳蛩颟鹳鹋鸫鸢慝觐觋贳瓯戡檠迓甙忒弋撖摁廾蘼蘖蘩蘅蘧藿藜藁藓薰薷薹薅薜薮蕹薏薇薨薤蕻蕲蕃瞢蕺蕞蕤蕨蕈蕙蓼蓿蔻蕖蔺蔟蔹蓰蔸甍蔌蓣蓥蒗蒴蒹蒡蓠蒺蒿蓊蓓蒽蓦蓐蓍蓁葭萱蒎蒌葶葩葆萼葸蒉葺蒈蒇葳葙葚葑葜菡菰萦菀菅菪菹菸萃萏菟菔萆萑萸萜菖菽菝萋萘堇菘菥萁菁莼莺莨莞莘荻莸荽莩莶荼莅莜莓莪莠莴莳荸莰荮荭荪荬荩茛荨荥荦茳茺茭荠茗荀荟荃荇荏茯荞莛茱茴茼莒茈荜荛荑茜苕苠茕茔茆茚茑苓苻茌苘苒苴苜茇茏苤苷茉苡芤苎苄芟芪芡芴芩苁苌苋芮芷芘苣苊苈芰芾芸芫芙芗芑芎芄芨芊芏艿艽艹鄞郾邛劐蒯赜匾匮匦叵匚廿茁著芝蒸斟蔗蘸藻葬匝蕴苑芋荧营萤莹英荫茵艺颐医药尧燕雅牙芽鸦薛靴蓄芯薪邪鞋萧巷项匣熙昔芜巫卧蔚萎苇藤萄苔蓑蒜苏斯薯蔬世式甚芍苫莎散萨若蕊茹荣蓉茸戎惹鹊颧区擎芹勤莲荔莉蕾勒劳蓝莱葵匡苦恐苛勘菌巨菊鞠敬警茎荆靳芥藉戒节蕉匠蒋荐茧荚蓟基惑或获荤茄切翘鞘巧蔷其七欺期蒲菩葡莆萍苹匹蓬藕殴鸥欧孽蔫匿慕募幕暮墓某莫蘑摹蔑藐苗蒙萌茂茅莽茫芒蔓落萝芦菱黄荒划花葫菏荷邯菇苟共贡汞巩恭功攻工葛革戈甘芬匪菲芳范藩贰董东蒂荡葱茨臣茶茬草藏苍蔡菜菠鞭蔽蓖苯薄苞靶芭鞍艾蔼",
            "B粜蚩颞聩聒聍聆聃耵骘孢孓孑屮隳凵勐鄹隰隗隍隈陴陲陬陧陟陔陉陂阼阽阪阱阡阢阝卺亟丞阻子孜坠职阵障陨院隅隐阴也耶阳逊限陷险隙卫陀陶孙祟隧随隋陕阮孺娶取了辽聊联孔聚阶降际聘陪聂陌孟陆陋陇隆陵函孩孤耿隔附防耳堕队陡耽聪除出耻承陈陛隘阿",
            "C鍪蟊蝥颡矜皴鹬鹜甬瞀怼炱毵骧骥骣骢骠骟骝骛骖骓骒骐骊骈骅骁骀驿驺驸驷驵婺逡迨迳巯畚弁厶邰刭驻骤蚤允驭豫预予又勇恿以矣验驯熊戏驼驮通台双驶圣骚桑叁柔劝驱垒骏驹颈劲骄艰鸡骑骗能难牟矛马骆骡驴欢骇观对叠邓怠驰骋叉参驳巴",
            "D鬣鬟鬓鬏鬈髹髭髻髫髯髦髡髟魇蹙趸翡舂蜚蜃虿耱耨耩耧耦耥耢耠耜耖耔耒鹩鹕鹌鸸鸪瓠礴礞礤礓磴礅磲磉磙磔碥碹碲碣碡碜碇碚碓碛硪硌硇硐硗硖硭硎砩砣砬砥砼砟砻砺砹砝砜砭斫砑砘砗砉砀矸矶恧恝焘斐飙欹腴挈臧戛彗尴尬尥尢匏耷奁夼劢酆劂剞刳赝靥厮厥厣厝厍仄嘏左奏鬃砖砧丈在砸耘愿原郁右友有尤硬页雁砚厌艳奄研压戌雄硝厢咸夏厦袭矽硒戊威万碗厅套碳态太泰碎肆硕爽耍戍寿石盛砷奢砂三辱确犬秦历砾励厉厘磊奎盔矿夸克磕砍厩韭兢礁硷碱慧灰磺牵砌契奇戚破砒碰硼砰裴耪碾奈耐面迈码碌垄聋龙硫磷胡厚耗夯硅顾故古辜龚耕感奉丰奋非矾而厄夺碟碉碘大达磋存磁蠢唇春础厨成辰厂碴厕布泵奔辈悲碑磅帮邦百耙碍",
            "E雎貔貘貅貊貂豸豕繇鹞燹膦臁膻臊朦臌膪膣滕膑膈媵塍腧腭腽腼腩腠腱腚腙腓腌腈脲脘脬脞脶豚脒朕胼胲脎脍胭胴胱胫胝朐胗胍胙胂胛胪胩胨胧肷肭肫肱肽朊肼肜肟刖虢爰戤孚尕邈彡豳奚垡郛鼐助逐肘肿脂肢胀脏孕月用臃盈臆胰腋舀遥腰悬须胸腥胁腺县膝腕妥脱腿腆腾膛胎舜甩受胜膳腮乳脸肋腊胯爵脚胶及肌且腔脐脾鹏朋膨胚胖脓腻脑乃膜觅貌脉貉股胳肛肝腹腑脯服肤肺肥肪肚胆脆肠彩采膊脖豹胞膀肮胺爱",
            "F鼋霾霰霭霪霎霏霈霁霆雯雳雩謦赭赧趱趑趔趄赳麴翥裘箐罄螫颢颉顸耋鹁盍瞽磬悫恚熹觳毂彀耄觏觌赍贲耆甏戟辇韬韫嫠遘逵逑懿鼙馨墀墚墉墁塬塥堠塄堙堞堀埭埽堍堋埝埤埸埯埴垸埒埙埚埘埕垠垴垧埏垲垌垤垭坳坶坭坨坻坼垆坫垅坩坂圻坜圯圮圹圳圪圬圩酃邗卦啬孛亓兀亍走专煮志址直支震真者赵增载哉栽运云越远袁垣元域雨壹堰严盐雪需墟幸协孝霄献现霞喜雾坞无斡未违韦顽土填替趟塘坦坛坍塔塌索寺霜士示十声赦墒丧韧壤却去趣趋求雷老垃坤亏款块垮坑壳坷考坎堪刊均救境井进劫截教嘉吉圾击魂卉墙乾起埔坡坪霹坯彭培霓南霉卖埋露零霖坏壶赫郝壕翰韩过圭规鼓垢埂赶干赴夫封坟坊二垛墩堆堵都动颠地堤戴寸矗翅赤城趁朝超场才裁埠勃博雹霸坝埃",
            "G餮餍鳌鏊靓豉豇麸纛翮蠹螯虺聱鹉鹂忑欤敕赉戬戋軎殪殡殛殚殍殓殒殄殇殂殁獒橐瓒璩璨璞璋璇璁璀璎璜瑾瑭瑷瑙瑕瑗瑜瑁琚琛琬琮琰琨琥琦瑛琪琏珲珞珧珩琊顼珙珥珈珉珀玳玷珑珂珏玟玢玮玑玎骜遨逦逋忝墼垩鄢郦郅郏邳邴剌芈噩孬鬲亘丕卅丐琢赘珠致至殖政正整臻珍盏责枣遭再玉与盂于迂夷一瑶殃焉亚殉邢形型刑下五武吾王玩豌歪瓦屯吞天琐速素死束殊事蚀珊瑟瑞融球琼青琴裂列两璃丽理琅赖来开玖静晋柬歼颊夹棘霍惠妻平琵琶弄囊末灭玫麦玛琉玲琳烈还环画互瑚瑰更副甫敷否珐恶顿毒逗豆靛到殆带歹刺曹残蚕不玻丙表碧逼甭班斑熬敖",
            "H虍瞿龌龊龉龈龇龆龅龃龀訾觜鹾粲虔颦颥鸬瞵瞰瞠瞟瞑瞌睽瞍睿睥睢睨睚睃睇睑睐眸眵眦眭眙眈眇盹眍眄盱砦忐氍觑觇赀攴遽壑矍卣乩丨紫桌卓瞩止睁贞战占瞻眨虞眼眩虚些瞎凸瞳眺瞬睡叔上龋眶肯卡具旧睛睫瞧歧颇频皮盼虐睦目瞄眠眯瞒虑虏卤颅卢龄虎睹督鼎盯点瞪此雌瞅齿柴餐睬步卜彪",
            "I鲨鲎鎏鋈裟淼泶沓懑氅挲尜娑逍灞灏瀵瀹瀛瀣瀚濯濠濞濮濡濂澶澹澧濉濑潺潼潲潸澌澍澉漩漉漪潴潋漶漯滹漕漤潇潆潢溟滂溏滏溴滗溷溻溽溧溥滢漭滠溘溱滟湄渥渲湔湓溆湟溲湫湎湮渫涮渌涫渖淙淝淦渑淠涿渎淞淅淇渚浣浼浠浜涔涓浞涠涞浯涑洳浔浒浏洚洵洮浍洫洎洙洄洇浈浃洌洧洹泾泯泓沱泮泫泺泖泠沲泗泱泸泷沭泔泐沩沆汶汴汩汨沌沔沐沅沣汊汜汔氵喾凼黉鼗渍滓淄滋注洲治滞汁浙兆沼涨掌漳湛沾渣泽澡源渊浴誉渔渝淤游油涌泳淫溢沂液耀漾洋演淹涯汛学汹兴泻泄肖小淆消削湘涎洗汐溪污沃涡温渭潍汪湾洼湍涂汀添涕淘逃滔涛烫淌棠堂汤潭滩汰溯水漱淑湿省渗沈深涉少裳尚赏汕沙涩洒润汝溶染雀渠泅清沁潦粱梁涟沥漓泪涝浪滥澜溃渴浚觉沮举酒浸津洁浇江涧溅渐尖济脊汲激活混浑汇辉浅潜洽泣汽沏柒漆瀑浦婆泼漂澎沛泡潘派湃沤浓泞涅溺泥淖漠沫渺泌没漫满洛沦滦滤潞漏流溜淋劣涣淮滑沪湖洪鸿涸河浩汉汗涵海光灌沽沟港溉涪浮汾沸泛法洱渡洞淀滇涤滴党当淡淬淳滁池澄沉尘澈潮敞常尝测沧泊渤波滨濒澳",
            "J蠼蠛蠊蟾蠓蠖蟮蟠蟪蟛蟀蟑蟓蟋螳螵螬蟥螃螗螭螅螈蟆蟒螨螓蝙蝤蝼蝣蝓螋蝮蝌蝰蝠蝻蝾蝽蜢螂蜿蜷蜩蜱蜴蝈蜾蜮蜥蜞蜻蜣蜉蜍蜊蜈蛸蛑蛘蛟蛴蛞蜓蛐蛳蛭蛲蛱蚴蛏蛉蚯蚱蚺蚰蛎蚵蛄蚶蚓蚪蚣蚧蚝蚬蚋蚍蚨虻虼虮虬煦曩曦曜曛暾暝暧暌暄晷晗晡晖晏晁晔晟昵昱昴曷炅昀昕昃杲昙昊旰旯戥韪暹遢夥勖刂禺昨最蛀蛛蜘照昭早晕曰遇愚蛹映影蝇易蚁曳野蜒蚜星蝎歇晓显贤暇虾晰晤蜗蚊旺晚蛙蜕题剔帅墅竖曙暑是时师肾申蛇晌晒蠕日蛆晴晾量里览蜡昆颗景晶紧鉴坚监晦蛔晃蝗曝暖螟明冕盟昧冒曼蚂螺临蝴虹旱果滚归蛊蛤蜂遏蛾蝶电旦虫匙晨畅昌蝉暴蚌昂暗",
            "K饕鼍黾躞躜躐躔躏躅蹴蹯蹼蹶蹰蹊蹒蹑蹂蹁蹉踱踽踵踹蹀踺踯踣踮踬踟踝踔跽踉跤跻跹跣跸跷跬跆跛跏跎跞跚跗跖跄趺趼趿趵颚鹭鹗戢璐囔嚯嚓嚅噼噻噫噱噤嚆噔噌噜噙噢噍嘬噗嘹噘嘭嘧嘀嗾嘣嘤嘁嘌嘈嘞嗤嗵嗨嗍嗌嗳嗲嗥嗯嗄嗝嗦嗔嗬嗫嗑嘟嗉嗷嗪喙喔喽嗟喑嗖啾喟喁喈喹喱喃嗒喋啜唰唳啷啶啵啖唷唼啐唿啕啁啭啉喵喏啧唪唧唑唏唣唢哳唔哽唠哧唛哞哏哝咤咪咩哜哚哙哌咿咻哕咣呲哔哓咦咧哒咴哂咭哐咝呦呶咄咛咚呤呱呷咔咂吲吣呙呗吡呃呖呔呓呒呋吆吖吒叻叨叩叽叱卟郢嘴足踪啄嘱咒忠盅中只趾吱咋喳噪躁咱郧跃员喻吁咏踊哟吟邑遗叶噎咬唁咽哑呀勋喧嘘嗅兄啸嚣哮响吓吸嘻吴呜嗡吻喂味唯哇唾吐听跳嚏啼蹄踢叹踏蹋唆虽嗽嗣嘶顺吮噬嗜史呻哨啥嗓嚷哩啦喇旷跨哭口吭啃咳咯咖喀鹃踞距咀叫嚼践呛遣器品啤喷呸跑咆趴啪呕哦啮呢呐哪鸣吗嘛骂吕路咙另唤患哗唬呼吼喉哄哼嘿喝呵号嚎喊哈贵跪剐咕跟嘎噶咐吩吠啡鄂跺哆蹲吨叮跌吊叼蹬蹈呆蹿吹串喘川躇踌吃逞呈吵嘲唱蹭踩哺别鄙蹦跋吧叭唉哎啊",
            "L黯黪黥黩黢黟黠黝黜錾袈罾羁罹罱罴罨詈罟罡罘疃畹畛畈畋畎畀町辚辘辏辎辍辋辄辂辁轾轼轺轹轷轸轶轵轳轲轱轭轫椠嬲迦圜圉圊圄囿囹囫囵囡囝囗勰哿罪浊转轴置辙罩辗斩轧暂圆园辕因鸭轩辖胃畏围团图田四思蜀署输软圈囚轻辆连力累困界较轿驾架甲加辑畸回堑黔畦圃毗畔男默墨皿逻罗轮略轰黑贺国辊轨固辅辐罚恩囤畴车边办罢",
            "M髑髌髋髂髅髀髁骼骺骶鹘骷骰骱罂颛鹦殳飚飕飓飑胄觊赙赕赇赈赆赅贻贶遄迥巅嶷嶝嶙嶂嵴嵩嵊嵫嵝嵯嵛嵬崽崴崾嵘崆崞崤崮崦崧崃崂峥峋峤峒峄岷峁岣岫岬岽岢岵岜岚岑岘岈岖岐岍屺岌幡幞幛幔幄帷帼帻帱帔帙帏兕夙罔冂剀刿赚贮周峙帜帧账帐崭赠贼则赃峪屿邮由婴屹鸯央岩崖峡巍网彤同帖贴炭岁髓赎赊赡删山肉冉曲岿凯峻巾见贱几贿幌凰峭嵌岂崎赔帕内帽赂岭骸骨购岗刚冈赋幅凤风峰贩凡帆峨剁朵赌雕典迪丹崔赐幢崇册财贬崩贝败凹盎岸",
            "N鐾羿襞胥疋鹨鹛愍熨擘甓檗璧嬖羼屦屣孱屙屐咫尻遐戕爿懵懔憷憧憔憬慵慊愫愎愀惴愣愕愦愠悴惚惆惘惝悱悻惬悛悌悒悃悝悭悚悖恽恪恂恺恻恹恸怡怿怊怫怩怍怏怛怦怵怙忸忭忪怆怅忾忤忡怄忮怃忏忖忉忄嵋崛奘巽乜昼怔展憎悦羽愉忧翌翼异忆乙已迅恤性惺心忻屑懈习犀惜悟屋慰尉尾惟惋臀屠恬屉惕巳司刷属书疏收恃屎虱尸慎慑屈情怜懒愧快慷慨剧惧局居惊尽届忌悸己悔恢恍惶怯悄恰屏譬屁劈怕懦尿尼恼悯民眉忙慢买屡履戮慌怀恒恨悍憾憨惯怪敢改愤飞发惰恫懂刁殿惦翟悼导蛋惮翠戳丑尺迟忱层惨惭怖避臂壁辟必懊",
            "O糨糗糅糈糍糌糇糁粽粼粞粝粑敉籼黼黻黹灬爝燧燔燠熠熵熳熘煺煸煊煅煨煜煳焱焯焖焓焐烊烨炫炷烀炻炝炖炜炀遴燮郯邺剡籽灼烛粘炸燥灶凿糟业焰炎烟烯熄烷烃糖烁数烧煽熔燃炔料燎粮炼粒类烙烂烤炕糠炬炯粳精烬火烩煌粕炮糯米迷煤炉娄焕糊烘焊糕烽粪粉烦断灯粹粗锤炊炽炒糙灿炳焙爆",
            "P衤窳窭皲襻襦襁褶褫褴褊褛褓褙褡裰裾裨裼褚裱裥裣裎裢裉袼袷裆袢袂衿衽衲衩窨窬窠窦窕窈窆窀穹穸鸩禳禧禚禊禅祺祧祯祠祗祢祚祓祜祛祉祆祀礻邃逭辶謇蹇寰褰寮寤搴骞甯宸宥宓宕宄宀廴郓冥冢冖剜祖宗字祝宙窒之寨窄宅宰灾冤裕寓宇寅宜揖窑宴穴宣袖写宵祥宪窝宛完袜褪突袒它宿宋守视室实审神社衫赛塞褥冗容裙穷寝寥帘礼牢窥宽裤窟寇空客军究窘襟窖家寂寄祸豁窃窍祁祈袍农宁寞幂密蜜寐裸禄窿宦宏鹤褐罕寒害冠官褂寡宫割富袱福额定祷窜窗穿初宠衬察补宾被宝袄案安",
            "Q鳢鳟鳝鳜鳙鳗鳕鳔鳓鳐鳏鳎鳍鳋鳊鳇鳆鳅鳄鲽鲼鲻鲺鲷鲶鲵鲴鲳鲲鲱鲰鲮鲭鲫鲩鲧鲦鲥鲣鲢鲡鲠鲟鲛鲚鲕鲔鲒鲑鲐鲋稣鲈鲇鲆鲅鲂鱿鑫觯觫觥觚觞觖斛袅鹱鸱鸲锺镳镲镱镯镬镫镪镩镨镧镦镥镤镢镡镝镟镞镛镙镘镗镖镔镓镒镏镎镌镉镆镅镄锵镂锿锾锼锸锷锶锴锲锱锬锩锫锪锢锟锞锝锛锘锖锕锔锓锒锏锎锍锊锉锇锆锂锃铿铽铼铹铷铵铴铳铯铮铫铩铪铨铧铥铤铢铠铟铞铛铘铙铗铖铕铒铑铐铎铍铌铋铊铉铈铄钿钽钼钺钹钸钷钶钴钲钰钯钬钭钪钫钤钣钜钛钚钕钗钔钐钏钍钌钊钋钇钆钅盥眢欷肴橥桀枭玺邂遛逖迩馕馔馓馑馐馍馊馇馄馀饽饷饴饬饫饪饩饨饧饣夤飧舛獾獯獬獠獗獍獐猱猸猬猥猹猢猕猝猞猊猡猓猗狻狺猃狳猁狷狴狲狩狯狨狒狍狎狁狃犸犷犴犰犭弑鬯劬奂刍郗郇郄邸邬凫匐訇匍勹刎刈氐爻丌钻邹锥铸猪皱钟炙争狰镇针锗詹铡匀钥怨猿鸳狱鱼犹铀迎印饮银逸铱旬锈匈凶猩锌蟹销象镶馅鲜锨狭铣夕希锡勿乌钨危外鸵兔铜铁锑獭锁饲氏饰狮勺煞刹杀色鳃锐饶然卿镣链镰吏鲤狸镭乐狼馈狂钧句锯狙灸久镜鲸锦金解饺角狡铰饯键钾急饥昏钦锹欠钳钱铅钎铺刨钮狞镍镊鸟馁钠名铭勉免猛锰镁贸卯铆锚猫馒锣卵铝鲁留馏铃猎猾狐忽猴狠锅龟逛馆够狗勾钩铬镐钢钙负孵锋饭犯钒饵尔儿饿多钝锻镀独兜锭钉钓甸狄岛错匆触雏锄钞猖铲馋猜铂钵狈钡鲍饱包镑",
            "R魑魍魈魉魃魅踅絷罅缶蜇颀鹎鸷瓞皤皓皎皈敫氲氪氤氩氡氚氙氘氕搿掰贽遑逅攮攥攉擢擤擗擐擀撺撙撸撷摺摭撄摞搡搦搌搠搛搋摅掾揆摒揎揞揄揿揠揸揲掼掮捩掊掬捭掴掎捺捱揶掭捃捋挹拶挢拮拗拚拊抻抟扪扌郫邾卮揍拙捉撞撰拽爪抓拄朱质制掷挚指执拯挣振蛰哲折找招摘扎择皂攒岳援拥殷抑掖摇邀氧扬掩押欣卸携挟掀舞捂握挝皖挽挖拓托拖推投捅挺挑提掏搪探摊抬挞所损擞搜撕拴摔抒授手势逝誓拭拾失摄捎擅扫搔撒揉扔热扰攘缺泉邱丘氰氢擒撂撩擂捞揽拦拉扩括捆魁挎扣抠控拷抗扛看揩掘抉攫撅捐据拒拘揪近斤捷接揭搅捡拣技挤挥皇撬抢扦掐气扑迫魄乒拼撇披批捧抨抛乓排拍爬挪扭牛拧捏捻撵年拈拟挠氖拇抹摸抿描抡掠氯掳搂拢拎换护后捍撼氦鬼罐拐挂瓜拱搁搞皋缸抚氟拂扶氛返反扼掇遁盾抖迭掉垫掂抵的捣挡氮掸担打搭挫措搓撮摧捶揣搐抽斥持撑掣撤扯抄掺搀拆搽插操擦捕帛搏拨播兵摈膘卑报抱拌扮扳搬拜摆白把拔扒捌翱按氨皑挨",
            "S醺醴醵醯醮醭醪醣醢醑醍醐醅醌酹酴酲酾酽酯酩酰酡酢酤酏酎酐酊覃皙懋檫檩檐檑橼橘樨樽橹檎樵橛樾橄槲樘樗槭樯槿榍槠榕槟槁榱槔榭榫榻榧榛楹楣楦榉槎榈榇槌椴楸榀楫榄楝楂楠椹楱椐棣楗椁椋棰椤棹椟棼楮棂桫梓桷桴梏梵栩桉桧桁桦桕栝梃桤桄桢桎桡桠栳栲柽柁栎柢枸柃栀柝枳柚枵柙栌枰柩栊柘栉柰杼杷枋枞枨杵枧枘杳杪枇枥杩杈杞杓杌鄄剽柞醉棕酌椎桩柱株植枝枕甄杖樟栈榨栅札酝榆酉樱椅椰要样杨酗朽杏醒械楔校橡想相檄西析梧桅枉椭桶酮桐梯桃檀酞梭酸粟酥松栓树述术梳枢柿梢杉森榷醛权栗李楞棱酪榔栏婪框酷枯可棵柯楷禁杰桔酵椒槛检贾枷极机桥橇枪棋栖朴票瓢飘棚配攀柠酿木模棉醚梦檬酶梅枚麓楼柳榴林桓槐横核杭酣棍柜桂棺构梗根格歌哥杠柑杆概覆枫焚酚樊杜栋顶丁档村醋醇椿椽楚橱酬橙郴查槽材柄彬标本杯棒榜梆板柏",
            "T齄鼾鼽黧鳘雒艨艟艚艏艋艉艄舾舴舳舻舸舫舨舯舭舣舢舡衄臬籀籁簸簦簪簟簋簖簏篼篾簌篪篦篥篚篝篌篁篑箴箫箢箜箪箅箨箝箬箸箧箦筱筲筢筻筮筠筝筌筵筅筚筘笞笾笳笤笥笠笱笮笙笪笸筇笏笫笊笕笄笃笈竽竺舐螽鹄鸹穰馥黏穑稷稹稔稞稂稃嵇稆秫秣秭秕雉矬矧眚憩愆歃牖牒牍攵氆氇毽毳毪犒犏犍犋犄牿牾牯牦牝昝榘逶逄迤迮迕夂衢徼徵徭徨徜徙徕後徉徇徂彷彳岙鄱郜籴汆劓睾毓胤囟夭乇丿纂租自篆筑竹舟重种稚秩智知征毡乍怎造赞粤御禹釉役移秧衍延衙循熏血选徐秀行衅笑向箱香舷衔先系悉息稀牺务物午我稳魏委微往颓徒秃透筒艇廷条舔甜特躺毯笋穗算艘私税黍释适矢剩升牲甥生身射舌稍扇筛入壬躯秋利篱黎犁梨篮筐筷科靠矩咎径筋街秸矫舰箭简笺稼季籍箕积稽秽徽簧乔签迁千迄乞片篇篷磐盘徘牌穆牧牡敏秒秘每么毛箩乱律篓笼徊乎衡很和禾航管乖刮箍躬各告稿篙秆竿复符逢繁翻番乏筏鹅舵躲短犊冬丢第笛敌等得德稻待答篡簇囱辞垂船处臭筹愁稠秤惩程乘称彻长策舱簿舶箔秉币彼笔鼻笨惫备版般稗笆奥矮",
            "U鲞蹩翦粢羲羰羯羧羟羝竦翊癯癫癖癜癔癞癍瘳瘾癃瘵瘿瘰瘭癀瘠瘢瘼瘛瘙瘕瘘瘥瘊瘗瘌瘅瘀瘐痿痼痱瘃痧痫痤痦痨痣痍痖痂痃疰疱痄疸疴疳疣疬疝疠疖疔疒鹣鹚鹈鹇蠲戆恣恙飒歆旮甑瓿猷槊桊孳妾遒丬阚阙阗阖阕阒阏阍阌阋阊阈阆阄阃闾闼闶闵闳闱闫闩啻鄯鄣邶凇冼冽冱冫衮兖夔冁馘遵尊总姿资咨兹着准状壮妆装痔郑症疹瘴彰章站闸曾韵阅痈音益毅意疫冶养痒羊疡彦沿阎颜阉丫癣羞新辛效翔羡闲问闻瘟头痛童剃誊疼痰瘫遂塑送朔兽瘦首韶商善闪闰瘸券拳痊酋亲疗凉立痢冷兰阑辣阔况竣决卷眷疽疚净竞竟靖痉疥竭郊交酱奖桨浆将将姜减剪兼煎间冀疾羌歉前凄普剖瓶瞥痞疲旁叛判疟凝逆闹闽闷门美六瘤凌羚凛豢痪痕阂闺关羹阁疙羔赣盖冯疯阀兑端痘斗冻凋奠递弟帝盗道郸单瘩瘁凑次瓷慈疵闯疮冲痴阐产差部并病冰瘪憋鳖辫辩辨弊敝闭痹迸背北半瓣疤癌",
            "V鼷鼹鼯鼬鼢暨艮舄舁臾鸠聿肀邕甾巛驽孥孀嬷嬗嬉嫜嫘嫦嫖嫱嫣媸嫔嫒媲嫫媾婷媛媪胬婵婢娼婕婊婧婀娓娣娴娲娉娌姹姘姣姝娆娅姗妯妲姒妤妞妫姊妗妣妪妩妍妃妁弩彐邋逯帑叟劭馗帚召杂娱尹姻姨姚妖巡寻婿絮旭姓嫌媳婉丸娃退迢她肃恕鼠始婶娠邵嫂如妊刃忍群隶姥恳垦郡君娟舅臼九姐剿娇建奸嫁妓既嫉即姬婚毁女怒努奴娘妮嫩奶娜那姆妙娩媚妹媒妈录灵好姑妇妨娥妒嫡刀逮巢剥",
            "W黛雠隽隼隹翕翎衾颔颌鹪鹆鸺畲龛恁爨煲歙毹牮觎瓴戗璺岱坌郐兮龠俎佥佘氽仝儋儇僮僦僬僭儆僖傺傩傧傥偻偬偎偈偕偃偾倨倥倌倜俾倭倮倏倬俳偌倩俸俟俑俜俣俚俅俪俨俦侔侬佼侪佻佾侏侃侉侑佴佶伽伲佗佟佝佚攸佧佞伫伉伧伥仵佤伢仳伛仞仫仡仨仂仉仃亻劁坐作做佐仔追住众仲侄值侦仗债欲愈逾俞余舆佑悠优佣亿倚仪伊依爷仰佯叙修休信斜像仙侠侮伍瓮翁位伪伟途偷停体倘贪他僳俗颂怂耸似伺舒售侍仕使食什伸舍伤傻僧伞儒仍任人仁全倾禽侵僚敛俩俐例傈儡佬傀侩俊倔倦俱仅今介借侥焦僵剑健件俭价假佳祭伎集货伙会俏侨仟企仆凭贫偏僻盆佩偶您念你倪拿命们仑伦侣令领伶赁鳞邻化华候侯盒合何含刽癸谷估公供个鸽父阜付傅斧釜俯俘伏佛忿份分仿伐俄段侗爹佃低凳登倒但袋贷代傣催促丛从创传储仇侈倡偿豺岔侧仓伯饼便倍堡保傍伴颁佰爸八傲俺",
            "X飨糸蠡颍鸶鸨犟畿幺缵缳缲缱缰缯缭缬缫缪缧缦缥缤缣缢缡缟缛缜缙缗缒缑缏缌缋缈缇缃缂缁绾绻绺绶缍绲绱绯绮绫绨绡绠绛绗绔绐绌绋绉绂绁绀纾纰纭纩纨纥纣纡纟鬻弼艴弭弪彘彖辔匕组纵综缀粥终纸旨织张绽约缘幼幽颖缨引绎肄彝疑绚续绪绣乡线弦纤细毋纹纬维统绦缩绥丝绳绅绍缮纱弱绒纫绕顷练缆绝绢纠经结皆绞缴疆缄纪继级缉绩绘强纽纳母缅绵弥络纶绿缕幻缓弧红弘贯弓给纲缚弗缝纷费纺缎缔弹绰纯绸弛缠编毖毙毕比绷绑绊",
            "Y麟麝鏖麒麋麈麇麂縻麾麽饔銮綮翩颏颃鹫鹧鹑鸾憝扉扈扃戽戾旖旒旎旌旃旄旆於斓齑膂肓昶栾娈膺廪廨廛廑廒赓庳庾庵庹庠庥庖庋庑庀奕弈壅塾劾邡邙邝谶谵谳谲谯谮谫谪谧谥谡谠谟谝谘谛谙谖谕谔谒谑谏谌谇谄谂谀诿诼诹诶诳诰诮诩诨诤诠诟诜诙诘诖诔诓诒诎诏诋诃诂讷讵讴讪讧讦讠羸蠃嬴禀裒脔亵袤亳亠丶座诅族卒谆庄主诛诸诌州衷证诊这遮肇斋诈育语诱永雍庸赢应鹰译谊议诣义裔亦衣夜谣谚言讶讯训询玄旋畜序许谢谐享详襄席误诬紊文谓为妄忘望亡弯庭亭讨唐谈谭诉诵讼说谁衰庶熟孰试市识诗施设认让瓤庆请廖谅亮良恋廉离朗郎廊谰廓库课刻亢康诀就京谨诫讲肩记计剂讥迹诲讳谎敲谴谦讫弃启旗齐谱评烹庞诺亩谋魔摩磨谬庙谜糜靡氓盲谩蛮麻论孪挛峦率旅鹿庐刘吝话户亨毫豪亥裹郭诡广雇庚膏高该讣腐府讽废诽放访房方讹敦度读订谍调店底诞词床充诚颤谗诧斌遍卞变扁庇褒谤哀",
            "！@#%…&*（）—；：”’？《》，。、~={}','|"};

    public String getDrug_content() {
        return drug_content;
    }
    public String getBig_unit_name() {
        return big_unit_name;
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }

    public String getUsages() {
        return usages;
    }

    public void setUsages(String usages) {
        this.usages = usages;
    }

    public String getContent_unit_name() {
        return content_unit_name;
    }

    public void setContent_unit_name(String content_unit_name) {
        this.content_unit_name = content_unit_name;
    }


    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }


    public void setBig_unit_name(String big_unit_name) {
        this.big_unit_name = big_unit_name;
    }

    public String getSmall_unit_name() {
        return small_unit_name;
    }

    public void setSmall_unit_name(String small_unit_name) {
        this.small_unit_name = small_unit_name;
    }

    public String getOrganization_code() {
        return organization_code==null?"":organization_code;
    }

    public void setOrganization_code(String organization_code) {
        this.organization_code = organization_code;
    }
    public void setDrug_content(String drug_content) {
        this.drug_content = drug_content;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getPinyin_code() {
        char[] t1  = getName().toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();

        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else
                    t4 += java.lang.Character.toString(t1[i]);
            }
            // System.out.println(t4);
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    public void setPinyin_code(String pinyin_code) {
        this.pinyin_code = pinyin_code;
    }

    public String getWubi_code() {
        StringBuffer result = new StringBuffer();
        String str=getName().toString();
        //用char循环取得每一个String的 字符
        for (int i = 0; i < str.length(); i++) {
            char temp1 = str.charAt(i);
            int asciicode = (int)temp1;
            //如果是254以内的ASCII 表示是西文字母和字符，直接显示，无需翻译
            if (asciicode >= 1 && asciicode <= 254) {
                result.append(temp1);
            }else{
                //循环遍历汉字五笔的字库（按照五笔首字符字符分组）
                for (int j = 0; j < WB.length; j++) {
                    char[] dbTmp = WB[j].toCharArray();
                    //循环遍历每个英文字母的字库
                    for (int k=1; k < dbTmp.length; k++) {
                        //找到
                        if((int)temp1==(int)dbTmp[k]){
                            //如果在WB最后一行，表明是中文标点符号，直接显示，否则显示对应的五笔首字母(该行的第1个字符)
                            if(j==WB.length-1){
                                result.append(dbTmp[k]);
                            }else {
                                result.append(dbTmp[0]);
                            }
                            break;
                        }
                    }
                }
            }
        }
        return result.toString();
    }

    public void setWubi_code(String wubi_code) {
        this.wubi_code = wubi_code;
    }

    public String getCustom_code() {
        return custom_code==null?"":custom_code;
    }

    public void setCustom_code(String custom_code) {
        this.custom_code = custom_code;
    }

   public String getUnit_content() {
        return unit_content==null?"":unit_content;
    }

   public void setUnit_content(String unit_content) {
        this.unit_content = unit_content;
    }

   public String getContent_unit() {
       return content_unit==null?"":content_unit;
    }

   public void setContent_unit(String content_unit) {
        this.content_unit = content_unit;
   }

    public String getDrug_type() {
        return drug_type;
    }

    public void setDrug_type(String drug_type) {
        this.drug_type = drug_type;
    }

    public String getDrug_spec() {
        return drug_spec;
    }

    public void setDrug_spec(String drug_spec) {
        this.drug_spec = drug_spec;
    }

    public String getSmall_unit() {
        return small_unit;
    }

    public void setSmall_unit(String small_unit) {
        this.small_unit = small_unit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBig_price() {
        return big_price;
    }

    public void setBig_price(String big_price) {
        this.big_price = big_price;
    }

    public String getSmall_price() {
        return small_price==null?"0":small_price;
    }

    public void setSmall_price(String small_price) {
        this.small_price = small_price;
    }

    public String getMedicine_type() {
        return medicine_type;
    }

    public void setMedicine_type(String medicine_type) {
        this.medicine_type = medicine_type;
    }

    public String getBbuy_price() {
        return bbuy_price;
    }

    public void setBbuy_price(String bbuy_price) {
        this.bbuy_price = bbuy_price;
    }

    public String getSbuy_price() {
        return sbuy_price;
    }

    public void setSbuy_price(String sbuy_price) {
        this.sbuy_price = sbuy_price;
    }

    public String getDrug_classification() {
        return drug_classification;
    }

    public void setDrug_classification(String drug_classification) {
        this.drug_classification = drug_classification;
    }

    public String getCharge_classification() {
        return charge_classification;
    }

    public void setCharge_classification(String charge_classification) {
        this.charge_classification = charge_classification;
    }

    public String getC_charge_classification() {
        return c_charge_classification;
    }

    public void setC_charge_classification(String c_charge_classification) {
        this.c_charge_classification = c_charge_classification;
    }


    public String getUse_level() {
        return use_level;
    }

    public void setUse_level(String use_level) {
        this.use_level = use_level;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLatin() {
        return Latin;
    }

    public void setLatin(String latin) {
        Latin = latin;
    }

    public String getDrug_category() {
        return drug_category;
    }

    public void setDrug_category(String drug_category) {
        this.drug_category = drug_category;
    }



    public String getMinchen() {
        return minchen;
    }

    public void setMinchen(String minchen) {
        this.minchen = minchen;
    }

    public String getStar_date() {
        return star_date;
    }

    public void setStar_date(String star_date) {
        this.star_date = star_date;
    }


    public Boolean getSocial_security() {
        return social_security;
    }

    public void setSocial_security(Boolean social_security) {
        this.social_security = social_security;
    }

    public Boolean getDrip() {
        return drip;
    }

    public void setDrip(Boolean drip) {
        this.drip = drip;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public Boolean getSurgery() {
        return surgery;
    }

    public void setSurgery(Boolean surgery) {
        this.surgery = surgery;
    }

    public Boolean getGynaecology() {
        return gynaecology;
    }

    public void setGynaecology(Boolean gynaecology) {
        this.gynaecology = gynaecology;
    }

    public Boolean getRecovered() {
        return recovered;
    }

    public void setRecovered(Boolean recovered) {
        this.recovered = recovered;
    }

    public Boolean getNew_medicine() {
        return new_medicine;
    }

    public void setNew_medicine(Boolean new_medicine) {
        this.new_medicine = new_medicine;
    }

    public Boolean getRecent_medicine() {
        return recent_medicine;
    }

    public void setRecent_medicine(Boolean recent_medicine) {
        this.recent_medicine = recent_medicine;
    }

    public Boolean getStock_medicine() {
        return stock_medicine;
    }

    public void setStock_medicine(Boolean stock_medicine) {
        this.stock_medicine = stock_medicine;
    }

    public String getNew_medicine_text() {
        return new_medicine_text;
    }

    public void setNew_medicine_text(String new_medicine_text) {
        this.new_medicine_text = new_medicine_text;
    }

    public String getRecent_medicine_text() {
        return recent_medicine_text;
    }

    public void setRecent_medicine_text(String recent_medicine_text) {
        this.recent_medicine_text = recent_medicine_text;
    }

    public String getStock_medicine_text() {
        return stock_medicine_text;
    }

    public void setStock_medicine_text(String stock_medicine_text) {
        this.stock_medicine_text = stock_medicine_text;
    }




    public String getAllergic_history() {

        return allergic_history== null ? "" : allergic_history.trim();
    }

    public void setAllergic_history(String allergic_history) {
        this.allergic_history = allergic_history;
    }

    public String getBig_unit() {
        return big_unit== null ? null : big_unit.trim();
    }

    public void setBig_unit(String big_unit) {
        this.big_unit = big_unit;
    }

    public String getSource() {
        return source==null?"":source.trim();
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public Boolean getPrin() {
        return prin;
    }

    public void setPrin(Boolean prin) {
        this.prin = prin;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCode() {
        return  code ==null ? "" : code.trim();
    }

    public void setCode(String code) {
        this.code =code;
    }


}