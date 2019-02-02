package com.baidu.homework.activity.web;

import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.webkit.WebView;
import com.baidu.homework.activity.base.ZybBaseActivity;
import com.baidu.homework.activity.web.actions.DefaultAction;
import com.baidu.homework.activity.web.actions.WebAction;
import com.zybang.annotation.WebActionContainer;

/**
 * 管理WebAction，通过静态的方式用字符串关联action和具体的WebAction类，按需加载
 */
public class WebActionManager {
    private static final String PREFIX = WebActionManager.class.getPackage().getName() + ".actions.";
    // private static final String ACTION_ADD_TO_CALENDAR = "AddToCalendarWebAction";
    //转人工客服
    // private static final String ACTION_OPEN_ONLINE_SERVICE = "openOnlineService";
    //打开机器人聊天页面
    // private static final String ACTION_OPEN_ROBOT_CHAT = "openRobotChat";
    //判断当前是否登录
    // private static final String ACTION_IS_LOGIN = "is_login";
    //调用Native登录操作
    // private static final String ACTION_LOGIN = "login";
    //调用Native绑定手机号操作
    // private static final String ACTION_BINDPHONE = "bindPhoneAlert";
    //调用端上条形码扫描
    // private static final String ACTION_BAR_CODE_SCANNING = "barcodeScanning";
    //调用Native登录操作
    // private static final String ACTION_RELOGIN_DIALOG = "reLoginDialog";

    //调用native登录操作,但是仅仅返回login的结果给fe,由fe判断进行reload还是跳转其他h5页面
    // private static final String ACTION_LOGIN_JUST_FOR_LOGIN_RESULT = "loginForResult";
    //显示一个toast
    // private static final String ACTION_TOAST = "toast";
    // private static final String ACTION_COLORFUL_TOAST = "colorfulToast";
    //退出当前activity
    // private static final String ACTION_EXIT = "exit";
    // 退出课中测试页面返回课堂练习
    // private static final String ACTION_EXIT_TO_PLAYER = "exitToPlayer";
    //调用Vibrator
    // private static final String ACTION_VIBRATE = "vibrate";
    //调用邀请对话框
    // private static final String ACTION_INVITE = "invite";
    //调用分享对话框
    // private static final String ACTION_SHARE = "share";
    //是否展示webActivity的分享按钮
    // private static final String ACTION_SHOW_SHARE = "show_share";
    //进入填写地址详情页面，如果填写成功，将会返回RESULT_OK
    // private static final String ACTION_ADDRESS = "address";
    //进入待解决列表页面
    // private static final String ACTION_UNRESOLVED_QUESTIONLIST = "unsolved_questionlist";
    //进入填学校页面
    // private static final String ACTION_FILL_SCHOOL = "fill_school";
    //进入学校圈
    // private static final String ACTION_GO_SCHOOL_CIRCLE = "go_school";
    //我的物品页
    // private static final String ACTION_MY_GIFT = "my_gift";
    //开始刮卡
    // private static final String ACTION_SCRAPE_CARD = "scrape_card";
    //赚刮刮卡
    // private static final String ACTION_EARN_CARD = "earn_card";
    //进入帖子页
    // private static final String ACTION_ARTICLE = "article";
    //进入商城
    // private static final String ACTION_MALL_HOME = "mall_home";
    //进入中考密卷
    // private static final String ACTION_MIDTEST = "midtest";
    //播放视频
    // private static final String ACTION_PLAYVIDEO = "playVideo";
    //播放视频,当是移动网络时提示用户,默认竖屏播放视频
    // private static final String ACTION_PLAYVIDEO_HINT = "playVideoHint";
    //获取公共参数
    // private static final String ACTION_COMMON = "common";
    //回答错误时通知客户端计时接口
    // private static final String ACTION_NOTICE = "notice";
    //练习中的跳转到下一页或者结果页,练习中的跳转到指定页
    // private static final String ACTION_FLIP_PAGE = "flipPage";
    //获取地理位置
    // private static final String ACTION_LOCATION = "getLocation";
    //通知用户的操作，选择了哪个答案
    // private static final String ACTION_GET_USERANSWER = "getPractiseResult";
    //在新页面打开一个WebActivity
    // private static final String ACTION_OPEN_WINDOW = "openWindow";
    //在新页面打开一个WebActivity
    // private static final String ACTION_CLOSE_AND_OPENWINDOW = "closeAndOpenWindow";
    //在检索结果页面的webview显示UGC入口
    // private static final String ACTION_UGC = "ugc";
    // private static final String ACTION_SHOW_UGC = "showUgc";
    //收藏本Item点击
    // private static final String ACTION_COLLECT_ITEMCLICK = "click";
    //收藏本加载更多
    // private static final String ACTION_COLLECT_LOADMORE = "loadmore";
    //新版错题本加载更多
    // private static final String ACTION_COLLECTION_LOAD_MORE = "collectionLoadMore";
    //收藏本空回调
    // private static final String ACTION_COLLECT_EMPTY = "loadempty";
    //售后的WebActivity页面的跳转
    // private static final String ACTION_AFTERSALE_JUMP = "jumptolist";
    //获取用户信息
    // private static final String ACTION_GET_USERINFO = "getuserinfo";
    // 检索结果页获取巩固练习数量
    // private static final String ACTION_EXERCISE_CNT = "exerciseCnt";
    //检索结果页调整巩固练习
    // private static final String ACTION_GO_TO_EXERCISE = "goToExercise";
    //跳举一反三
    // private static final String ACTION_LEARN_BY_ANALOGY = "learnByAnalogy";
    // 跳转到直播课的首页
    // private static final String ACTION_GO_TO_LIVE_CLASS = "goToLiveTab";

    // 取得用户在主live页所选的年级,默认为初中20
    // private static final String ACTION_GET_SELECT_GRADE = "getSelectGrade";
    // 跳转到直播课的班课列表页
    // private static final String ACTION_GO_TO_CLASS_LIST = "goToClassList";
    // 跳转到首页改版二级班课页面
    // private static final String ACTION_GO_TO_COURSE_LIST = "goToLiveCourseList";
    // 跳转到直播课的详情页
    // private static final String ACTION_GO_TO_CLASS_DETAIL = "goToClassDetail";
    // 跳转到直播课的首页
    // private static final String ACTION_GO_TO_LESSON_LIST = "goToLessonList";
    // 跳转到优惠券的页面
    // private static final String ACTION_GO_TO_COUPON_LIST = "goToCouponList";
    // 跳转到直播课教师的粉丝列表页
    // private static final String ACTION_GO_TO_TEACHER_FANS = "teacherFans";
    //跳转到我的课程列表页面
    // private static final String ACTION_GO_TO_MY_COURSE_LIST = "goToMyCourseList";
    //新手大礼包任务完成
    // private static final String ACTION_FINISH_GIFT = "finishGift";
    //去拍题
    // private static final String ACTION_OPEN_CAMERA = "openCamera";

    //将文字复制到native粘贴板
    // private static final String ACTION_COPY_TO_CLIPBOARD = "copyToClipboard";
    //调起native拨号
    // private static final String ACTION_DIAL = "dial";
    //包月支付action
    // private static final String ACTION_MONTH_PAY = "tutorpay";
    //留言页面右上角显示详情页按钮
    // private static final String ACTION_CHAT_SHOW_DETAIL = "chatShowDetail";
    //跳转到绑定手机号页面
    // private static final String ACTION_GO_BIND_PHONE = "goBindPhone";
    //申诉完成
    // private static final String ACTION_COMPLETE_APPEAL = "completeAppeal";

    //展示web页里的大图
    // private static final String ACTION_SHOW_WEB_LARGE_PICTURE = "showWebPicture";

    //黑珍珠展示多图
    // private static final String ACTION_SHOW_WEB_MULTIPICTURE = "showWebMultiPicture";

    //webActivity中是否禁用手势返回
    // private static final String ACTION_SWAP_BACK = "swapBack";

    //检索结果页pager滑动是否禁用
    // private static final String ACTION_SEARCH_RESULT_NATIVE_SCROLL = "searchResultNativeScroll";

    //进入订单
    // private static final String ACTION_OPEN_SALE_LIST = "openSalelist";

    //打开缓存列表
    // private static final String ACTION_OPEN_CACHE_LIST = "openCachelist";

    //SearchResult 页面缩放动作开始
    // private static final String ACTION_SEARCH_RESULT_ZOOM_START = "searchResultZoomStart";
    // SearchResult页面跳到知识卡片的action
    // private static final String ACTION_SEARCH_RESULT_TO_KNOWLEDGE_CARD = "knowledgeCard";
    // 一对一辅导跳转到拍题老师列表页
    // private static final String ACTION_TUTOR_JUMP_TO_ASK_LIST = "jumptoasklist";
    //英语单元作文中拼完作文素材到得到完整的一篇作文，更新文章详情页的rightbutton的action
    // private static final String ACTION_COMPLETE_CONSTRUCT_COMPOSITION = "showComposition";
    //英文单元作文详情首页返回到作文列表，h5需要这个action解一个bug
    // private static final String ACTION_BACK_COMPOSITION_LIST = "backCompositionList";
    //文言文全文及译文页面点击跳转到更多词义页面
    // private static final String ACTION_CLASSICAL_BACK_TO_CLASSICAL_MORE_MEANING_PAGE = "backToClassicalMoreMeaningPage";
    //直播课，课程详情页面，点击去支付
    // private static final String ACTION_PAY_LIVE_COURSE = "payLiveCourse";
    //直播课，点击去支付,传入支付参数
    // private static final String ACTION_PAY_LIVE_COURSE_WITH = "payLiveCourseWith";
    //直播课，课程详情页面，点击教师详情
    // private static final String ACTION_GOTO_LIVE_TEACHER_DETAIL = "gotoLiveTeacherDetail";
    //直播课，跳转到教师的课程列表页
    // private static final String ACTION_GOTO_TEACHER_COURSE_LIST = "gotoTeacherCourseList";
    //直播课，跳转到我的课表页面
    // private static final String ACTION_GOTO_MY_COURSE_TABLE = "gotoMyCourseTable";
    //直播课，添加完收货地址
    // private static final String ACTION_AFTER_SAVE_ADDRESS = "afterSaveAddressAction";
    //直播课，fe选中了某个选择题的答案
    // private static final String ACTION_HOMEWORK_SELECT = "homeworkSelectAction";
    //直播课，fe索要目前选中的题目答案
    // private static final String ACTION_HOMEWORK_GET_RESULT = "homeworkGetResultAction";
    //基础业务 习题本列表页，点击某道题跳转到题目详情页面
    // private static final String ACTION_LIVE_EXERCISE_BOOK_GO_DETAIL="subjectDetail";
    //基础业务 习题本列表页，点击某道题跳转到PDF预览下载页面
    // private static final String ACTION_LIVE_EXERCISE_BOOK_GO_PDF="exportPdf";
    //全屏
    //课后作业是否wifi
    //fe日志action
    // private static final String ACTION_FE_LOGCAT="feLogcat";
    // private static final String ACTION_LIVE_LESSON_KEY_BORARD_HIGHT="liveKeyBoardHideHeight";
    //跳转Activity统一action
    // private static final String ACTION_START_ACTIVITY = "startActivity";
    //调用Native登录操作,使用于邦邦商城
    // private static final String ACTION_LOGIN_MALL = "loginMall";
    // private static final String ACTION_WEB_VIEW_INDEX = "webviewIndex";
    // private static final String ACTION_PIC_SEARCH_RESULT_GUIDE = "picSearchResultGuide";
    // private static final String ACTION_UNIVERSAL_LAUNCH_PAGE = "universalLaunchPage";
    // private static final String ACTION_GET_SID = "getSid";
    // private static final String ACTION_FEEDBACK = "feedback";
    // private static final String ACTION_DOWNLOAD_PEN = "downLoadPenData";//下载笔迹数据解析成JSON传给fe
    //跳转EvaluationActivity
    // private static final String ACTION_GOTO_EVALUATION = "goEvaluation";
    //在fe页面增加投诉按钮
    // private static final String ACTION_ADD_FEEDBACK = "addFeedback";
    // 调微博分享文字接口，用于"话题"
    // private static final String ACTION_WEIBO_SHARE = "weiboShare";
    // 视频回放页菜单弹出
    // private static final String ACTION_PLAYBACK_MENU = "playbackMenu";
    // 隐藏输入法框
    // private static final String ACTION_HIDE_INPUT = "hideInput";
    // 设置页面随输入法伸缩
    // private static final String ACTION_SET_SOFT_INPUT_RESIZE = "setSoftInputResize";
    //跳转到个人信息界面的action
    // private static final String ACTION_GO_TO_PERSONAL_INFOR = "goToPersonalInfor";
    //feed details 判断linkurl 是不是 null
    // private static final String ACTION_IS_SHOWVIDEOSINARTICLE= "isShowVideosInArticle";
    //禁止/恢复端上页面随嵌套webview滚动的响应开关Action
    // private static final String ACTION_MIXEDPAGEWEBVIEWNESTEDSCROLLENABLE= "mixedPageWebviewNestedScrollEnable";
    
    //作文大赛投稿的action
    // private static final String ACTION_COMPOSITION_GAME_CONTRIBUTE = "contributeComposition";
    //作文大赛出书的action
    // private static final String ACTION_COMPOSITION_PUBLISH = "publishComposition";
    //作文大赛投稿时完善个人信息
    // private static final String ACTION_COMPOSITION_COMPLETE_INFO = "completeInfo";
    //fe调用手机自带浏览器 打开网页的action
    // private static final String ACTION_OPEN_WEB_PAGER = "openWebPager";
    //跳转到新版我的老师列表页
    // private static final String ACTION_TUTOR_TO_LIST = "tutortolist";
    //排行榜显示右上角按钮
    // private static final String ACTION_SHOW_RANK_INFO = "rankInfo";
    //跳转到套餐列表页面
    // private static final String ACTION_TUTOR_PACK = "tutorpack";
    //显示跳转投诉的对话框
    // private static final String ACTION_SHOW_COMPLAINT = "faqShowComplaint";
    //显示联系我们对话框
    // private static final String ACTION_SHOW_CONTACT_US = "faqShowContactUs";
    //显示我的客服按钮
    // private static final String ACTION_SHOW_UDESK_ENTRY = "faqShowUdeskEntry";
    //显示右上角辅导记录按钮
    // private static final String ACTION_SHOW_SINGLE_TEACHER_ORDER = "faqShowTeacherOrder";
    //显示右上角专属老师更换按钮
    // private static final String ACTION_SHOW_CHANGE_EXCLUSIVE_TEACHER = "faqShowChangeTeacher";
    //显示ufo反馈界面
    // private static final String ACTION_SHOW_UFO = "faqShowUfo";
    //升级检测
    // private static final String ACTION_UPDATE_CHECK = "updateCheck";
    //跳转到新老用户首页
    // private static final String ACTION_GOTO_FUDAO_ENTRY = "gotoFudaoEntry";
    //跳转到无tab的新老用户首页
    // private static final String ACTION_GOTO_FUDAO_HOME = "goAskTeacherHome";
    //文言文背诵控制录音按钮显示
    // private static final String ACTION_RECITE_RECORD_VOICE_BUTTON_SHOW = "reciteRecordVoiceButtonShow";
    //辅导跳转端内页面统一action
    // private static final String ACTION_OPEN_NA_PAGE = "openNaPage";
    //下载多媒体数据（图片，视频，音乐，文档，通用）
    // private static final String ACTION_DOWNLOAD_MEDIA = "downloadMedia";
    //直播课课程详情页,改变购物车数量的action
    // private static final String ACTION_CHANGE_LIVE_DETAIL_CART = "changeLiveDetailCart";
    //为h5运营提供的拍照或从图库取一个图片上传
    // private static final String ACTION_CAMERA_UPLOAD = "cameraUpload";
    //打开原生浏览器
    // private static final String ACTION_OPEN_BROWSER = "openBrowser";
    //端内打开ADX下载浏览器
    // private static final String ACTION_OPEN_ADX_DOWNLOAD = "openADXDownload";
    //在新页面打开一个LiveOrderWebActivity
    // private static final String ACTION_OPEN_ORDER_WINDOW = "openOrderWindow";
    //存储kv数据
    private static final String ACTION_MEM_DATA = "memData";
    //是否禁止back键（只能用于WebActivity中）
    // private static final String ACTION_FORBID_BACK = "forbidBack";
    //跳转到具体的试卷
    private static final String ACTION_GO_TO_SPECIFIC_TEST_PAPER = "openSpecificTestPaper";
    //跳转到试卷首页
    private static final String ACTION_GO_TO_TEST_PAPER_HOME_PAGE = "openTestPaperHomePage";
    //跳转到称号修改页面
    // private static final String ACTION_GO_TO_USER_TITLE = "goToUserTitleAction";
    //跳转到用户资料修改页面
    // private static final String ACTION_GO_TO_USER_PROFILE = "goToUserProfile";
    //拍照导流到文言文搜索结果页
    private static final String ACTION_GO_TO_CLASSICAL_CHINESE_SEARCH = "goToClassicalChineseSearch";
    //通用跳转的action
    // private static final String ACTION_APP_JUMP_PROTOCOL = "APPJumpProtocol";
    //搜索结果无结果跳转到反馈页面
    private static final String ACTION_GO_TO_SEARCH_RESULT_FEEDBACK = "goToFeedback";
    //跳转到平台通用支付页
    // private static final String ACTION_PLATFORM_PAY = "platformPay";

    //从fe页面发起呼叫老师
    // private static final String ACTION_CALL_TEACHER = "callTeacher";
    // 口算fe页面请求获取题目数据
    // private static final String ACTION_ORAL_CALCULATE_GET_DATA = "OralCalculateGetData";
    // 口算fe页面提交用户答案
    // private static final String ACTION_ORAL_CALCULATE_SUBMIT_ANSWER = "OralCalculateSubmitAnswer";
    // 口算错题解析fe页面请求获取题目数据
    // private static final String ACTION_ORAL_CALCULATE_REVIEW_GET_DATA = "OralCalculateReviewGetDataAction";

    //留言中上传图片
    // private static final String ACTION_FUDAO_CHAT_SEND_IMAGE = "askTeacherChatSendImage";
    //扫码回传给FE页面
    // private static final String ACTION_SCAN_CODE = "scanQRCode";

    //第三方广告统计
    // private static final String ACTION_AD_STAT = "adstat";
    //课程详情页点击购物车按钮/选课单
    // private static final String ACTION_GO_SHOPCAR = "goShopCar";
    //录播课售卖
    // private static final String ACTION_PLAY_LIVE_VIDEO = "playLiveVideo";
    //微课跳转到试卷详情页
    // private static final String ACTION_TINY_COURSE_PAPER = "openTinyCoursePaper";
    //微课测试包跳转到试卷详情页
    // private static final String ACTION_TINY_COURSE_PAPER_TEST = "openTinyCourseTestPaper";
    //跳转到试卷详情页
    // private static final String ACTION_ANSWER_PAPER_TEST = "openAnswerPaperProcess";
    //同步练习试卷结果页后退
    // private static final String ACTION_SYNC_PRACTICE_EXIT = "syncPracticeExit";
    //请求Feed流广告
    // private static final String ACTION_FETCH_FEED_AD = "fetchFeedAd";
    //展现Feed流广告
    // private static final String ACTION_SHOW_FEED_AD = "showFeedAd";
    //点击Feed流广告
    // private static final String ACTION_CLICK_FEED_AD = "clickFeedAd";
    //删除Feed流广告
    // private static final String ACTION_REMOVE_FEED_AD = "removeFeedAd";
    //打开游戏WebActivity
    // private static final String ACTION_OPEN_GAME_WINDOW = "openGameWindow";
    //打开游戏奖励弹窗
    // private static final String ACTION_OPEN_GAME_REWARD_BOX = "openGameRewardBox";
    //在日历中添加或删除预约成功的事件
    // private static final String ACTION_ADD_BOOK_EVENT_TO_CALENDAR = "askTeacherAddReserveAlarm";
    //判断是否是优学派设备
    // private static final String ACTION_JUDGE_UPLAY_DEVICE = "judgeUplayDevice";
    //新版开始游戏
    // private static final String ACTION_GAME_EVENT_START = "gameEventStart";
    //更新VIP提问体验官数据
    // private static final String ACTION_UPDATE_ASK_VIP = "updateAskVip";
    //答疑练习报告跳转问老师页面
    // private static final String ACTION_AT_ASK_TEACHER="ataskteacher";
    //练习报告老用户登录
    // private static final String ACTION_REPORT_ENTRANCE_OLDLOGIN="reportEntranceOldLogin";
    //1对1系列课老师列表
    // private static final String ACTION_SERIAL_COURSE_ENTRY="serialCourseTeacher";
    //Nlog打点
    private static final String ACTION_NLOG="nlog";

    /*IM*/
    //跳转到作业详情页面
    // private static final String ACTION_IMEXERCISE_DETAIL_INFO = "imexercisedetailinfo";

    /*查看新作业，关闭activity*/
    // private static final String ACTION_CHECK_NEW_HOMEWORK = "checkNewHomework";

    /*查看新作业，解决完型填空上下分屏展示查看新作业的view*/
    // private static final String ACTION_NEW_HOMEWORK_REFRESH_UI = "newHomeworkRefreshUi";

    /*课后作业点击提交按钮*/
    // private static final String ACTION_SUBMIT_HOMEWORK = "submitHomework";

    /*课后作业答题卡点击题号*/
    // private static final String ACTION_TRANS_POSITIOIN = "transPosition";

    /*课后作业录音左右滑动*/
    // private static final String ACTION_SOUND_RECORD_SWITCH = "soundrecordswitch";

   /* 课后作业与期末考试多点登录*/
    // private static final String ACTION_HOMEWORK_AND_FINALEXAM_LOGIN = "homeworkAndFinalExamLogin";
    /*作业dialog*/
    // private static final String ACTION_HOMEWORK_DIALOG = "homeworkDialog";

    /*提交作业状态（loading/fail/success）*/
    // private static final String ACTION_HOMEWORK_SUBMIT_TOAST = "homeworkSubmitToast";

    /*获取完型填空答案*/
    // private static final String ACTION_RECEIVE_QUESTION_ANSWER = "receiveQuestionAnswer";
// 
    /**
     * 期末考试，课后作业录入习题本
     */
    // private static final String ACTION_QUESTION_COLLECT_STATE = "questionCollectState";

    /*期末考试  start*/
    //引导页 - 立即答题
    // private static final String ACTION_LIVE_BASE_FINAL_EXAM_START_ANSWER = "LiveBaseFinalExamStartAnswer";
    //倒计时页面 - 查看答题卡
    // private static final String ACTION_LIVE_BASE_FINAL_EXAM_CHECK_ANSWER_CARD = "LiveBaseFinalExamCheckAnswerCard";
    // 答题卡页 - 点击课程主页
    // private static final String ACTION_LIVE_BASE_FINAL_EXAM_COURSE_MAIN_PAGE = "LiveBaseFinalExamCourseMainPage";
    // 答题卡页 - 查看考试结果页
    // private static final String ACTION_LIVE_BASE_FINAL_EXAM_SHOW_RESULTVIEW = "LiveBaseFinalExamShowResultView";

    //广告点击功能根据btype内部跳转、外部跳转、下载等
    // private static final String ACTION_AD_COMMON_CLICK = "commonClick";
    //滑动退出滑动冲突上报冲突控件
    // private static final String ACTION_SLIDING_EXIT_STATE = "slidingExitState";
    //下载广告查看状态
    // private static final String ACTION_AD_DOWNLOAD_STATUS = "adxDownloadStatus";
    // 跳转商城和首页
    // private static final String ACTION_GO_TO_MALL_INDEX = "GoToMallIndexAction";
    // 跳转商城商品详情
    // private static final String ACTION_GO_TO_MALL_GOOD_DETAIL = "GoToMallGoodsDetailAction";
    // 跳转商城商品详情
    // private static final String ACTION_GO_TO_MALL_ACTIVITY_GOODS = "GoToMallActivityGoodsAction";
    // 跳转商城商品详情
    // private static final String ACTION_GO_TO_MALL_GOODS_LIST = "GoToMallGoodsListAction";
    // 跳转商城确认订单
    // private static final String ACTION_GO_TO_MALL_ORDER_CONFIRM = "GoToMallOrderConfirmAction";
    // 跳转商城确认订单
    // private static final String ACTION_GO_TO_MALL_ORDER_DETAIL = "GoToMallOrderDetailAction";
    // 知识宇宙 跳转到播放页面
    // private static final String ACTION_UNIVERSE_GOTO_PLAY_FM = "universeKnowledgePlayFM";
    // 知识宇宙 跳转到首页
    // private static final String ACTION_UNIVERSE_GOTO_HOME = "universeKnowledgeHome";
    // 知识宇宙 跳转到专辑列表页
    // private static final String ACTION_UNIVERSE_GOTO_ALBUM_LIST = "universeKnowledgeAlbum";
    // 吊起知识宇宙专辑够买弹窗
    // private static final String ACTION_UNIVERSE_SHOW_ALBUM_BUY = "universeAlbumBuy";
    // 吊起知知识宇宙分享
    // private static final String ACTION_UNIVERSE_SHOW_SHARE = "universeShowShare";
    // private static final String ACTION_START_LIVE_LESSON = "startLiveLesson";
    /*期末考试  end*/

    //问题详情页
    // private static final String ACTION_OPEN_QUESTIONDETAIL = "questiondetail";

    //h5插件
    // private static final String ACTION_H5_HTTPREQUST = "httpRequst";
    // private static final String ACTION_STAT_EVENT = "statEvent";
    // private static final String ACTION_LOG_REPORT = "logReport";
    // private static final String ACTION_LOG_CAT = "logcat";
    // private static final String ACTION_LIVE_EXIT_WEB_PAGE = "liveExitWebPage";//关闭h5页面
    // private static final String ACTION_LIVE_OPEN_WEB_PAGE = "liveOpenWebPage";//打开h5页面
    // private static final String ACTION_LIVE_CLOSE_WEB_CACHE_VC = "closeWebCacheVc";//关闭LiveWebCacheActivity页面
    // private static final String ACTION_WEB_CACHE_FORBID_BACK = "webCacheForbidBack";//关闭LiveWebCacheActivity页面
    // private static final String ACTION_WEB_GRADE_LODING_TYPE = "loadingTypeClose";//关闭学部定制loading动画
    // private static final String ACTION_OPEN_LIVE_WINDOW = "openLiveWindow";
    // private static final String ACTION_SHOW_CACHE_ACTIVITY_SHARE = "showCacheActivityShare";

    //课中签到
    // private static final String ACTION_PLAY_AUDIO = "playAudio";
    //纯净播放器
    // private static final String ACTION_PLAY_PURE_VIDEO = "playPureVideo";

    //倍速播放器
    // private static final String ACTION_MULTIPLE_PLAY = "platformRTCVideo";

    //页面刷新通知
    // private static final String ACTION_NOTIFICATION_ACTION = "postNotificationAction";

    //跳转相似习题列表
    // private static final String ACTION_FORECAST_JUMP_SIMILAR = "forecastJumpSimilar";

    //跳转相似习题详情页
    // private static final String ACTION_GO_SIMILAR_DETAIL = "goSimilarDetail";

    //跳转到搜索题目详情页
    // private static final String ACTION_GO_SEARCH_RESULT = "goSearchResult";

    /*课中FE插件 END*/
    //存储action名称和对应的WebAction处理类之间的对应关系

    // private static final String ACTION_LAOSHISHUO_IMAGE_PREVIEW="imgPreview";
    // private static final String ACTION_LAOSHISHUO_COMMENT_REPLY="commentReply";
    // private static final String ACTION_LAOSHISHUO_TEACHER_MSG_DETAIL="teacherMessageDetail";

    private static final String ACTION_JUMP_READWORLD = "jumpReadWorld";
    //跳转帮主开讲
    // private static final String ACTION_JUMP_TO_BANG_ZHU = "jumpToBangZhu";
    //跳转到个人主页
    // private static final String ACTION_JUMP_TO_USER_CARD = "jumpToUserCard";
    //拍照搜题返回收藏id
    // private static final String ACTION_SEARCH_FAVORITE_RESULT = "searchFavoriteResult";
    //拍照搜题修改收藏状态
    // private static final String ACTION_CHANGE_COLLECTION = "changeCollection";
    //真题列表跳转
    // private static final String ACTION_OPEN_PRESENTATION_PAGER = "openPresentationPaper";
    private static ArrayMap<String, String> WEB_ACTION_MAP = new ArrayMap<>();

    static {
        WEB_ACTION_MAP.put(ACTION_SHOW_WEB_MULTIPICTURE, PREFIX + "ShowWebMultiPictureAction");
        WEB_ACTION_MAP.put(ACTION_FILL_SCHOOL, PREFIX + "FillSchoolWebAction");
        WEB_ACTION_MAP.put(ACTION_TOAST, PREFIX + "ToastWebAction");
        WEB_ACTION_MAP.put(ACTION_COLORFUL_TOAST, PREFIX + "ColorfulToastAction");
        WEB_ACTION_MAP.put(ACTION_EXIT, PREFIX + "ExitWebAction");
        WEB_ACTION_MAP.put(ACTION_EXIT_TO_PLAYER, PREFIX + "ExitWebAction");
        WEB_ACTION_MAP.put(ACTION_IS_LOGIN, PREFIX + "IsLoginWebAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_ONLINE_SERVICE, PREFIX + "OpenOnlineServiceWebAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_ROBOT_CHAT, PREFIX + "OpenRobotChatWebAction");
        WEB_ACTION_MAP.put(ACTION_ADD_TO_CALENDAR, PREFIX + "AddToCalendarWebAction");
        WEB_ACTION_MAP.put(ACTION_LOGIN, PREFIX + "LoginWebAction");
        WEB_ACTION_MAP.put(ACTION_RELOGIN_DIALOG, PREFIX + "LiveReLoginDialogWebAction");
        WEB_ACTION_MAP.put(ACTION_LOGIN_JUST_FOR_LOGIN_RESULT, PREFIX + "LoginJustForResultWebAction");
        WEB_ACTION_MAP.put(ACTION_VIBRATE, PREFIX + "VibrateWebAction");
        WEB_ACTION_MAP.put(ACTION_INVITE, PREFIX + "InviteWebAction");
        WEB_ACTION_MAP.put(ACTION_SHARE, PREFIX + "ShareWebAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_CACHE_LIST, PREFIX + "OpenCacheListWebAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_SALE_LIST, PREFIX + "OpenSaleListWebAction");
        WEB_ACTION_MAP.put(ACTION_SHOW_SHARE, PREFIX + "ShowShareBtnWebAction");
        WEB_ACTION_MAP.put(ACTION_ADDRESS, PREFIX + "AddressWebAction");
        WEB_ACTION_MAP.put(ACTION_UNRESOLVED_QUESTIONLIST, PREFIX + "QuestionListWebAction");
        WEB_ACTION_MAP.put(ACTION_GO_SCHOOL_CIRCLE, PREFIX + "SchoolCircleWebAction");
        WEB_ACTION_MAP.put(ACTION_MY_GIFT, PREFIX + "MyGiftWebAction");
        WEB_ACTION_MAP.put(ACTION_SCRAPE_CARD, PREFIX + "ScrapCardWebAction");
        WEB_ACTION_MAP.put(ACTION_EARN_CARD, PREFIX + "EarnCardWebAction");
        WEB_ACTION_MAP.put(ACTION_ARTICLE, PREFIX + "ArticleWebAction");
        WEB_ACTION_MAP.put(ACTION_MALL_HOME, PREFIX + "MallHomeWebAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_LIVE_CLASS, PREFIX + "LiveHomeWebAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_CLASS_LIST, PREFIX + "LiveClassWebAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_COURSE_LIST, PREFIX + "CourseListWebAction");

        WEB_ACTION_MAP.put(ACTION_GO_TO_LESSON_LIST, PREFIX + "LiveLessonListWebAction");
        WEB_ACTION_MAP.put(ACTION_GET_SELECT_GRADE, PREFIX + "LiveSelectGradeWebAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_COUPON_LIST, PREFIX + "CouponListWebAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_CLASS_DETAIL, PREFIX + "ClassDetailWebAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_TEACHER_FANS, PREFIX + "LiveTeacherFansWebAction");
        WEB_ACTION_MAP.put(ACTION_MIDTEST, PREFIX + "MidTestWebAction");
        WEB_ACTION_MAP.put(ACTION_LOCATION, PREFIX + "LocationWebAction");
        WEB_ACTION_MAP.put(ACTION_PLAYVIDEO, PREFIX + "PlayVideoWebAction");
        WEB_ACTION_MAP.put(ACTION_PLAYVIDEO_HINT, PREFIX + "PlayVideoHintWebAction");
        WEB_ACTION_MAP.put(ACTION_COMMON, PREFIX + "CommonWebAction");
        WEB_ACTION_MAP.put(ACTION_NOTICE, PREFIX + "NoticeWebAction");
        WEB_ACTION_MAP.put(ACTION_FLIP_PAGE, PREFIX + "FlipPageWebAction");
        WEB_ACTION_MAP.put(ACTION_GET_USERANSWER, PREFIX + "GetUserAnswerWebAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_WINDOW, PREFIX + "OpenWindowWebAction");
        WEB_ACTION_MAP.put(ACTION_CLOSE_AND_OPENWINDOW, PREFIX + "closeAndOpenWindowWebAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_ORDER_WINDOW, PREFIX + "OpenOrderWindowWebAction");
        //阶段性测试
        WEB_ACTION_MAP.put(ACTION_FE_LOGCAT, PREFIX + "LiveBaseFeLogcatWebAction");
        WEB_ACTION_MAP.put(ACTION_LIVE_LESSON_KEY_BORARD_HIGHT, PREFIX + "LiveLessonKeyBorardHeightAction");
        //摸底测试
        //课后作业
        WEB_ACTION_MAP.put(ACTION_SOUND_RECORD_SWITCH,PREFIX+"LiveBaseTestSoundRecordSwitchAction");
        //课后作业
        WEB_ACTION_MAP.put(ACTION_LIVE_LESSON_KEY_BORARD_HIGHT, PREFIX + "LiveLessonKeyBorardHeightAction");
        WEB_ACTION_MAP.put(ACTION_SHOW_UGC, PREFIX + "ShowUgcWebAction");
        WEB_ACTION_MAP.put(ACTION_UGC, PREFIX + "UgcWebAction");
        WEB_ACTION_MAP.put(ACTION_COLLECT_ITEMCLICK, PREFIX + "CollectItemClickAction");
        WEB_ACTION_MAP.put(ACTION_COLLECT_LOADMORE, PREFIX + "CollectLoadAction");
        WEB_ACTION_MAP.put(ACTION_COLLECT_EMPTY, PREFIX + "CollectEmptyAction");
        WEB_ACTION_MAP.put(ACTION_AFTERSALE_JUMP, PREFIX + "AfterSaleJumpAction");
        WEB_ACTION_MAP.put(ACTION_GET_USERINFO, PREFIX + "GetUserInfoAction");
        WEB_ACTION_MAP.put(ACTION_COPY_TO_CLIPBOARD, PREFIX + "CopyToClipboardAction");
        WEB_ACTION_MAP.put(ACTION_DIAL, PREFIX + "DialAction");
        WEB_ACTION_MAP.put(ACTION_SHOW_WEB_LARGE_PICTURE, PREFIX + "ShowWebLargePictureAction");
        WEB_ACTION_MAP.put(ACTION_EXERCISE_CNT, PREFIX + "ExerciseCntWebAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_EXERCISE, PREFIX + "GoExerciseWebAction");
        WEB_ACTION_MAP.put(ACTION_LEARN_BY_ANALOGY, PREFIX + "GoExerciseNormalWebAction");
        WEB_ACTION_MAP.put(ACTION_FINISH_GIFT, PREFIX + "FinishGiftAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_CAMERA, PREFIX + "OpenCameraAction");
        WEB_ACTION_MAP.put(ACTION_SWAP_BACK, PREFIX + "SwapBackAction");
        WEB_ACTION_MAP.put(ACTION_SEARCH_RESULT_NATIVE_SCROLL, PREFIX + "SearchResultNativeScrollAction");
        WEB_ACTION_MAP.put(ACTION_SEARCH_RESULT_ZOOM_START, PREFIX + "SearchResultZoomStartAction");
        WEB_ACTION_MAP.put(ACTION_MONTH_PAY, PREFIX + "TutorPayAction");
        WEB_ACTION_MAP.put(ACTION_SEARCH_RESULT_TO_KNOWLEDGE_CARD, PREFIX + "ResultToKnowledgeCardAction");
        WEB_ACTION_MAP.put(ACTION_TUTOR_JUMP_TO_ASK_LIST, PREFIX + "TutorJumpToAskListAction");
        WEB_ACTION_MAP.put(ACTION_COMPLETE_CONSTRUCT_COMPOSITION, PREFIX + "CompleteCompositionAction");
        WEB_ACTION_MAP.put(ACTION_BACK_COMPOSITION_LIST, PREFIX + "BackToEnglishUnitCompositionList");
        WEB_ACTION_MAP.put(ACTION_CLASSICAL_BACK_TO_CLASSICAL_MORE_MEANING_PAGE, PREFIX + "ClassicalJumpWebAction");
        WEB_ACTION_MAP.put(ACTION_PAY_LIVE_COURSE, PREFIX + "PayLiveCourseAction");
        WEB_ACTION_MAP.put(ACTION_PAY_LIVE_COURSE_WITH, PREFIX + "PayLiveCourseWithAction");
        WEB_ACTION_MAP.put(ACTION_GOTO_LIVE_TEACHER_DETAIL, PREFIX + "GotoLiveTeacherDetailAction");
        WEB_ACTION_MAP.put(ACTION_START_ACTIVITY, PREFIX + "StartActivityAction");
        WEB_ACTION_MAP.put(ACTION_GOTO_TEACHER_COURSE_LIST, PREFIX + "GotoTeacherCourseListAction");
        WEB_ACTION_MAP.put(ACTION_LOGIN_MALL, PREFIX + "LoginMallWebAction");
        WEB_ACTION_MAP.put(ACTION_WEB_VIEW_INDEX, PREFIX + "WebViewIndexAction");
        WEB_ACTION_MAP.put(ACTION_PIC_SEARCH_RESULT_GUIDE, PREFIX + "PicSearchResultGuideAction");
        WEB_ACTION_MAP.put(ACTION_UNIVERSAL_LAUNCH_PAGE, PREFIX + "UniversalStartActivityAction");
        WEB_ACTION_MAP.put(ACTION_GET_SID, PREFIX + "GetSidAction");
        WEB_ACTION_MAP.put(ACTION_FEEDBACK, PREFIX + "FeedbackWebAction");
        WEB_ACTION_MAP.put(ACTION_WEIBO_SHARE, PREFIX + "WeiboShareAction");
        WEB_ACTION_MAP.put(ACTION_DOWNLOAD_PEN, PREFIX + "DownLoadPenDataWebAction");
        WEB_ACTION_MAP.put(ACTION_GOTO_EVALUATION, PREFIX + "GotoEvaluationAction");
        WEB_ACTION_MAP.put(ACTION_ADD_FEEDBACK, PREFIX + "AddFeedBackAction");
        WEB_ACTION_MAP.put(ACTION_PLAYBACK_MENU, PREFIX + "PlayBackMenuAction");
        WEB_ACTION_MAP.put(ACTION_HIDE_INPUT, PREFIX + "HideInputAction");
        WEB_ACTION_MAP.put(ACTION_SET_SOFT_INPUT_RESIZE, PREFIX + "SetSoftInputResizeAction");
        WEB_ACTION_MAP.put(ACTION_GOTO_MY_COURSE_TABLE, PREFIX + "GotoMyCourseTableAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_MY_COURSE_LIST, PREFIX + "GoToMyCourseListAction");
        WEB_ACTION_MAP.put(ACTION_COMPOSITION_GAME_CONTRIBUTE, PREFIX + "CompositionContributeAction");
        WEB_ACTION_MAP.put(ACTION_COMPOSITION_PUBLISH, PREFIX + "PublishCompositionAction");
        WEB_ACTION_MAP.put(ACTION_COMPOSITION_COMPLETE_INFO, PREFIX + "CompositionContributeCompleteInfoAction");
        WEB_ACTION_MAP.put(ACTION_AFTER_SAVE_ADDRESS, PREFIX + "AfterSaveAddressAction");
        WEB_ACTION_MAP.put(ACTION_HOMEWORK_SELECT, PREFIX + "HomeworkSelectAction");
        WEB_ACTION_MAP.put(ACTION_HOMEWORK_GET_RESULT, PREFIX + "HomeworkGetResultAction");
        WEB_ACTION_MAP.put(ACTION_LIVE_EXERCISE_BOOK_GO_DETAIL, PREFIX + "LiveSubjectDetailWebAction");
        WEB_ACTION_MAP.put(ACTION_LIVE_EXERCISE_BOOK_GO_PDF, PREFIX + "LiveExportPdfWebAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_WEB_PAGER, PREFIX + "OpenPhoneWebPagerAction");
        WEB_ACTION_MAP.put(ACTION_TUTOR_TO_LIST, PREFIX + "TutorToListAction");
        WEB_ACTION_MAP.put(ACTION_SHOW_RANK_INFO, PREFIX + "RankInfoAction");
        WEB_ACTION_MAP.put(ACTION_TUTOR_PACK, PREFIX + "TutorPackAction");
        WEB_ACTION_MAP.put(ACTION_SHOW_COMPLAINT, PREFIX + "ShowComplaintAction");
        WEB_ACTION_MAP.put(ACTION_SHOW_CONTACT_US, PREFIX + "ShowContactAction");
        WEB_ACTION_MAP.put(ACTION_SHOW_UDESK_ENTRY, PREFIX + "ShowUDeskEntryAction");
        WEB_ACTION_MAP.put(ACTION_SHOW_SINGLE_TEACHER_ORDER, PREFIX + "ShowTeacherOrderListBtnAction");
        WEB_ACTION_MAP.put(ACTION_SHOW_CHANGE_EXCLUSIVE_TEACHER, PREFIX + "ShowChangeExclusiveTeacherBtnAction");
        WEB_ACTION_MAP.put(ACTION_SHOW_UFO, PREFIX + "ShowUfoAction");
        WEB_ACTION_MAP.put(ACTION_UPDATE_CHECK, PREFIX + "UpdateCheckAction");
        WEB_ACTION_MAP.put(ACTION_GOTO_FUDAO_ENTRY, PREFIX + "GotoFudaoEntryAction");
        WEB_ACTION_MAP.put(ACTION_GOTO_FUDAO_HOME, PREFIX + "GotoAskTeacherHomeAction");
        WEB_ACTION_MAP.put(ACTION_RECITE_RECORD_VOICE_BUTTON_SHOW, PREFIX + "ReciteRecordVoiceButtonShowAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_NA_PAGE, PREFIX + "FudaoOpenNaPageAction");
        WEB_ACTION_MAP.put(ACTION_DOWNLOAD_MEDIA, PREFIX + "DownloadMediaAction");
        WEB_ACTION_MAP.put(ACTION_CHANGE_LIVE_DETAIL_CART, PREFIX + "ChangeDetailCartNumberAction");
        WEB_ACTION_MAP.put(ACTION_CAMERA_UPLOAD, PREFIX + "CameraUploadAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_BROWSER, PREFIX + "OpenBrowserWebAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_PERSONAL_INFOR, PREFIX + "GoToPersonalInforAction");
        WEB_ACTION_MAP.put(ACTION_MEM_DATA, PREFIX + "MemDataWebAction");
        WEB_ACTION_MAP.put(ACTION_FORBID_BACK, PREFIX + "ForbidBackWebAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_SPECIFIC_TEST_PAPER, PREFIX + "GoToSpecificTestPaperAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_TEST_PAPER_HOME_PAGE, PREFIX + "GoToTestPaperHomePageAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_USER_TITLE, PREFIX + "GoToUserTitleAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_USER_PROFILE, PREFIX + "GoToUserProfileAction");
        WEB_ACTION_MAP.put(ACTION_CALL_TEACHER, PREFIX + "CallTeacherAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_CLASSICAL_CHINESE_SEARCH, PREFIX + "GoToClassicalChineseSearchAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_SEARCH_RESULT_FEEDBACK, PREFIX + "GoToSearchResultFeedBackAction");
        WEB_ACTION_MAP.put(ACTION_PLATFORM_PAY, PREFIX + "PlatformPayAction");
        WEB_ACTION_MAP.put(ACTION_FUDAO_CHAT_SEND_IMAGE, PREFIX + "FudaoChatSendImageAction");
        WEB_ACTION_MAP.put(ACTION_APP_JUMP_PROTOCOL, PREFIX + "APPJumpProtocolAction");
        WEB_ACTION_MAP.put(ACTION_ORAL_CALCULATE_GET_DATA, PREFIX + "OralCalculateGetDataAction");
        WEB_ACTION_MAP.put(ACTION_ORAL_CALCULATE_SUBMIT_ANSWER, PREFIX + "OralCalculateSubmitAnswerAction");
        WEB_ACTION_MAP.put(ACTION_ORAL_CALCULATE_REVIEW_GET_DATA, PREFIX + "OralCalculateReviewGetDataAction");
        WEB_ACTION_MAP.put(ACTION_SCAN_CODE, PREFIX + "ScanCodeAction");
        WEB_ACTION_MAP.put(ACTION_APP_JUMP_PROTOCOL, PREFIX + "APPJumpProtocolAction");
        WEB_ACTION_MAP.put(ACTION_GO_SHOPCAR, PREFIX + "GoShopCarAction");
        WEB_ACTION_MAP.put(ACTION_AD_STAT, PREFIX + "AdStatWebAction");
        WEB_ACTION_MAP.put(ACTION_PLAY_LIVE_VIDEO, PREFIX + "PlayLiveVideoAction");
        WEB_ACTION_MAP.put(ACTION_CHAT_SHOW_DETAIL, PREFIX + "FudaoChatShowDetailAction");
        WEB_ACTION_MAP.put(ACTION_GO_BIND_PHONE, PREFIX + "GoBindPhoneAction");
        WEB_ACTION_MAP.put(ACTION_COMPLETE_APPEAL, PREFIX + "CompleteAppealAction");
        WEB_ACTION_MAP.put(ACTION_TINY_COURSE_PAPER, PREFIX + "OpenTinyCoursePaperAction");
        WEB_ACTION_MAP.put(ACTION_TINY_COURSE_PAPER_TEST, PREFIX + "OpenTinyCoursePaperTestAction");
        WEB_ACTION_MAP.put(ACTION_ANSWER_PAPER_TEST, PREFIX + "OpenAnswerPaperProcessAction");
        WEB_ACTION_MAP.put(ACTION_SYNC_PRACTICE_EXIT, PREFIX + "SyncPracticeExitAction");
        WEB_ACTION_MAP.put(ACTION_FETCH_FEED_AD, PREFIX + "FetchFeedAdAction");
        WEB_ACTION_MAP.put(ACTION_SHOW_FEED_AD, PREFIX + "ShowFeedAdAction");
        WEB_ACTION_MAP.put(ACTION_CLICK_FEED_AD, PREFIX + "ClickFeedAdAction");
        WEB_ACTION_MAP.put(ACTION_REMOVE_FEED_AD, PREFIX + "RemoveFeedAdAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_GAME_WINDOW, PREFIX + "OpenGameWindowWebAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_GAME_REWARD_BOX, PREFIX + "OpenGameRewardBox");
        WEB_ACTION_MAP.put(ACTION_ADD_BOOK_EVENT_TO_CALENDAR, PREFIX + "FudaoAddBookEventToCalendarAction");
        WEB_ACTION_MAP.put(ACTION_JUDGE_UPLAY_DEVICE, PREFIX + "FudaoJudgeUplayDeviceAction");
        WEB_ACTION_MAP.put(ACTION_GAME_EVENT_START, PREFIX + "GameEventStartAction");
        WEB_ACTION_MAP.put(ACTION_UPDATE_ASK_VIP, PREFIX + "UpdateAskVipAction");
        WEB_ACTION_MAP.put(ACTION_AT_ASK_TEACHER, PREFIX + "AtAskTeacherAction");
        WEB_ACTION_MAP.put(ACTION_REPORT_ENTRANCE_OLDLOGIN, PREFIX + "ReportEntranceOldLoginAction");
        WEB_ACTION_MAP.put(ACTION_SERIAL_COURSE_ENTRY, PREFIX + "SerialCourseEntryAction");
        WEB_ACTION_MAP.put(ACTION_NLOG, PREFIX + "NLogAction");
        WEB_ACTION_MAP.put(ACTION_IMEXERCISE_DETAIL_INFO, PREFIX + "ImExeiciseDetailInfoAction");
        WEB_ACTION_MAP.put(ACTION_HOMEWORK_AND_FINALEXAM_LOGIN, PREFIX + "HomeworkAndFinalExamLoginAction");
        WEB_ACTION_MAP.put(ACTION_SUBMIT_HOMEWORK, PREFIX + "SubmitHomeworkAction");
        WEB_ACTION_MAP.put(ACTION_TRANS_POSITIOIN, PREFIX + "TransPositionAction");
        WEB_ACTION_MAP.put(ACTION_CHECK_NEW_HOMEWORK, PREFIX + "CheckNewHomeworkAction");
        WEB_ACTION_MAP.put(ACTION_NEW_HOMEWORK_REFRESH_UI, PREFIX + "NewHomeworkRefreshUiAction");
        WEB_ACTION_MAP.put(ACTION_HOMEWORK_DIALOG, PREFIX + "HomeworkDialogAction");
        WEB_ACTION_MAP.put(ACTION_HOMEWORK_SUBMIT_TOAST, PREFIX + "HomeworkSubmitToastAction");
        WEB_ACTION_MAP.put(ACTION_RECEIVE_QUESTION_ANSWER, PREFIX + "ReceiveQuestionAnswerAction");
        WEB_ACTION_MAP.put(ACTION_QUESTION_COLLECT_STATE,PREFIX + "questionCollectState");
        WEB_ACTION_MAP.put(ACTION_LIVE_BASE_FINAL_EXAM_START_ANSWER, PREFIX + "LiveBaseFinalExamStartAnswerAction");
        WEB_ACTION_MAP.put(ACTION_LIVE_BASE_FINAL_EXAM_CHECK_ANSWER_CARD, PREFIX + "LiveBaseFinalExamCheckAnswerCardAction");
        WEB_ACTION_MAP.put(ACTION_LIVE_BASE_FINAL_EXAM_COURSE_MAIN_PAGE, PREFIX + "LiveBaseFinalExamCourseMainPageAction");
        WEB_ACTION_MAP.put(ACTION_LIVE_BASE_FINAL_EXAM_SHOW_RESULTVIEW, PREFIX + "LiveBaseFinalExamShowResultViewAction");

        WEB_ACTION_MAP.put(ACTION_AD_COMMON_CLICK, PREFIX + "CommonClickAction");
        WEB_ACTION_MAP.put(ACTION_SLIDING_EXIT_STATE, PREFIX+"SlidingExitStateAction");
        WEB_ACTION_MAP.put(ACTION_UNIVERSE_GOTO_HOME,PREFIX+"GotoUniverseHomeAction");
        WEB_ACTION_MAP.put(ACTION_UNIVERSE_GOTO_ALBUM_LIST,PREFIX+"GotoUniverseAlbumAction");
        WEB_ACTION_MAP.put(ACTION_UNIVERSE_GOTO_PLAY_FM,PREFIX+"GotoUniverseFMAction");
        WEB_ACTION_MAP.put(ACTION_UNIVERSE_SHOW_ALBUM_BUY,PREFIX+"ShowUniverseAlbumBuy");
        WEB_ACTION_MAP.put(ACTION_UNIVERSE_SHOW_SHARE,PREFIX+"UniverseShareWebAction");
        WEB_ACTION_MAP.put(ACTION_AD_DOWNLOAD_STATUS,PREFIX+"AdDownloadStatusAction");

        WEB_ACTION_MAP.put(ACTION_GO_TO_MALL_INDEX,PREFIX+"GoToMallIndexAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_MALL_GOOD_DETAIL,PREFIX+"GoToMallGoodsDetailAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_MALL_ORDER_CONFIRM,PREFIX+"GoToMallOrderConfirmAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_MALL_ORDER_DETAIL,PREFIX+"GoToMallOrderDetailAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_MALL_ACTIVITY_GOODS,PREFIX+"GoToMallActivityGoodsAction");
        WEB_ACTION_MAP.put(ACTION_GO_TO_MALL_GOODS_LIST,PREFIX+"GoToMallGoodsListAction");
        WEB_ACTION_MAP.put(ACTION_START_LIVE_LESSON,PREFIX+"StartLiveLesson");

        WEB_ACTION_MAP.put(ACTION_LAOSHISHUO_IMAGE_PREVIEW, PREFIX+"ImagePreviewAction");
        WEB_ACTION_MAP.put(ACTION_LAOSHISHUO_COMMENT_REPLY, PREFIX+"CommentReplyAction");
        WEB_ACTION_MAP.put(ACTION_LAOSHISHUO_TEACHER_MSG_DETAIL, PREFIX+"TeacherMessageDetailAction");

        WEB_ACTION_MAP.put(ACTION_H5_HTTPREQUST,PREFIX+"H5HttpRequestAction");
        WEB_ACTION_MAP.put(ACTION_STAT_EVENT,PREFIX+"StatEventAction");
        WEB_ACTION_MAP.put(ACTION_LOG_REPORT,PREFIX+"LogReportAction");
        WEB_ACTION_MAP.put(ACTION_LOG_CAT,PREFIX+"LogCatAction");

        WEB_ACTION_MAP.put(ACTION_JUMP_READWORLD,PREFIX+"JumpReadWorldAction");
        WEB_ACTION_MAP.put(ACTION_PLAY_AUDIO,PREFIX+"LivePlayAudioAction");
        WEB_ACTION_MAP.put(ACTION_PLAY_PURE_VIDEO,PREFIX+"PlayPureVideoAction");

        WEB_ACTION_MAP.put(ACTION_MULTIPLE_PLAY,PREFIX+"MultipleVideoAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_ADX_DOWNLOAD,PREFIX+"OpenADXDownloadWebAction");

        WEB_ACTION_MAP.put(ACTION_OPEN_QUESTIONDETAIL,PREFIX+"OpenQuestionDetailsWebAction");

        WEB_ACTION_MAP.put(ACTION_IS_SHOWVIDEOSINARTICLE,PREFIX+"ShowVideosInArticleAction");

        WEB_ACTION_MAP.put(ACTION_MIXEDPAGEWEBVIEWNESTEDSCROLLENABLE,PREFIX+"AdNestedScrollEnableAction");

        WEB_ACTION_MAP.put(ACTION_NOTIFICATION_ACTION,PREFIX+"PostNotificationAction");
        WEB_ACTION_MAP.put(ACTION_FORECAST_JUMP_SIMILAR,PREFIX+"SimilarExerciseAction");
        WEB_ACTION_MAP.put(ACTION_GO_SIMILAR_DETAIL,PREFIX+"SimilarExerciseDetailAction");
        WEB_ACTION_MAP.put(ACTION_GO_SEARCH_RESULT,PREFIX+"SearchResultJumpAction");
        WEB_ACTION_MAP.put(ACTION_JUMP_TO_BANG_ZHU,PREFIX+"BangZhuAction");
        WEB_ACTION_MAP.put(ACTION_JUMP_TO_USER_CARD,PREFIX+"UserCardAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_LIVE_WINDOW, PREFIX + "OpenLiveWindowAction");
        WEB_ACTION_MAP.put(ACTION_SHOW_CACHE_ACTIVITY_SHARE, PREFIX + "ShowCacheActivityShareAction");
        WEB_ACTION_MAP.put(ACTION_LIVE_EXIT_WEB_PAGE, PREFIX + "LiveExitWebPageAction");
        WEB_ACTION_MAP.put(ACTION_LIVE_OPEN_WEB_PAGE, PREFIX + "LiveOpenWebPageAction");
        WEB_ACTION_MAP.put(ACTION_LIVE_CLOSE_WEB_CACHE_VC, PREFIX + "CloseWebCacheVcAction");
        WEB_ACTION_MAP.put(ACTION_WEB_CACHE_FORBID_BACK, PREFIX + "WebCacheForbidBackAction");
        WEB_ACTION_MAP.put(ACTION_WEB_GRADE_LODING_TYPE, PREFIX + "CloseGradeLoadingAction");
        WEB_ACTION_MAP.put(ACTION_BINDPHONE, PREFIX + "IsVerifyWebAction");
        WEB_ACTION_MAP.put(ACTION_BAR_CODE_SCANNING, PREFIX + "BarCodeScanningAction");
        WEB_ACTION_MAP.put(ACTION_SEARCH_FAVORITE_RESULT, PREFIX + "SearchFavoritResultAction");
        WEB_ACTION_MAP.put(ACTION_CHANGE_COLLECTION, PREFIX + "ChangeCollectionAction");
        WEB_ACTION_MAP.put(ACTION_COLLECTION_LOAD_MORE, PREFIX + "CollectLoadMoreAction");
        WEB_ACTION_MAP.put(ACTION_OPEN_PRESENTATION_PAGER, PREFIX + "OpenPresentationAction");
    }

//    如有新增action,请同步到到wiki文档中备案
//    http://wiki.afpai.com/pages/viewpage.action?pageId=2133144


    private static final String[] FINDER_SUFFIXES = {"CartoonBook", "SyncPractice", "GameFeed", "LiveCommon", "TeachingUI", "SpeakPractice","TeachingPlugin","TeachingTest"};
    /**
     * 通过Action名称获取对应的WebAction对象
     *
     * @param action action名称，对应的定义在{@link WebActionManager}中
     * @return 如果存在处理这个action的WebAction则返回，否则返回null
     */
    public static WebAction getAction(String action) {
        String clazzName = WEB_ACTION_MAP.get(action);
        if (TextUtils.isEmpty(clazzName)) {
            clazzName = WebActionContainer.getActionFinder(FINDER_SUFFIXES).findAction(action);
        }

        try {
            Class clazz = Class.forName(clazzName);
            WebAction webAction = (WebAction) clazz.newInstance();
            try {
                clazz.getDeclaredMethod("onActivityResult", ZybBaseActivity.class, WebView.class, int.class, int.class, Intent.class);
                webAction.isNeedOnActiviyResult = true;
            } catch (Exception ex) {
            }
            return webAction;
        } catch (Exception e) {
        }
        return new DefaultAction();
    }
}
