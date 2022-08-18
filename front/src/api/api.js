const HOST = 'https://i7a704.p.ssafy.io:8080/api/'

const USER = 'user/'
const REDIS = 'redis/'
const REPORTS = 'reports/'
const BOARDS = 'boards/'
const RELATIONSHIP = 'relationship/'
const MAIN = 'main/'

export default {
  reports: {
    reportsList: () => HOST + REPORTS,
    reportsDetail: (reportsId) => HOST + REPORTS + 'detail/' + reportsId + '/'
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

  main: {
    selectrooms: (lat, lng) => HOST + REDIS + 'selectRooms' + '/' + lat + '/' + lng,
    getrandombus: () => HOST + REDIS + 'getRandomBus',
    fetchCurrentUser: userId => HOST + USER + userId,
    changeIsRouletted: userId => HOST + USER + userId + '/roulette',
    getMyBuses: (userId) => HOST + MAIN + 'topbus/' + userId
  },
  chat: {
    createBoard: () => HOST + BOARDS,
    deleteBoard: boardId => HOST + BOARDS + boardId,
    getBoards: busId => HOST + BOARDS + busId,

    getIsAccessible: (sessionId, lat, lng) => HOST + REDIS + 'isAccessibleRoom/' + lat + '/' + lng + '/' + sessionId,
    getRoomInfo: sessionId => HOST + REDIS + 'rooms/' + sessionId,
    getProfileUserData: (userId, sessionId, targetId) => HOST + RELATIONSHIP + userId + '/' + sessionId + '/' + targetId
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
