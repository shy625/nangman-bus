const HOST = 'http://i7a704.p.ssafy.io:8080/api/'

const USER = 'user/'
// const ARTICLES = 'articles/'
// const COMMENTS = 'comments/'

export default {
  accounts: {
    login: () => HOST + USER + 'login/',
    logout: () => HOST + USER + 'logout/',
    signup: () => HOST + USER,
    currentUserInfo: (userid) => HOST + USER + userid, // 유저 정보, 수정, 삭제
    // username으로 프로필 제공
    profile: userid => HOST + USER + 'profile/' + userid,
  },
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
