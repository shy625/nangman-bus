const HOST = 'http://i7a704.p.ssafy.io:8080/api/'

const USER = 'user/'
const HOME = 'home/'
const REPORTS = 'reports/'
// const ARTICLES = 'articles/'
// const COMMENTS = 'comments/'

export default {
  reports: {
    reportsList: () => HOST + REPORTS,
  },
  accounts: {
    login: () => HOST + USER + 'login/',
    logout: () => HOST + USER + 'logout/',
    signup: () => HOST + USER,

    // get: 유저정보 조회, post: 오늘의 닉네임 제공, delete: 회원 탈퇴
    currentUserInfo: (userid) => HOST + USER + userid + '/',
    // username으로 프로필 제공
    profile: userid => HOST + USER + 'profile/' + userid,
  },

  // 홈 화면에서 보여줄 실시간 버스 데이터 3대
  home: {
    realTimeData: () => HOST + HOME + 'realtimedata/',
  }
//   articles: {
//     // /articles/
//     articles: () => HOST + ARTICLES,
//     // /articles/1/
//     article: articlePk => HOST + ARTICLES + `${articlePk}/`,
//     likeArticle: articlePk => HOST + ARTICLES + `${articlePk}/` + 'like/',
//     comments: articlePk => HOST + ARTICLES + `${articlePk}/` + COMMENTS,
//     comment: (articlePk, commentPk) =>
//       HOST + ARTICLES + `${articlePk}/` + COMMENTS + `${commentPk}/`,
//   },
}
