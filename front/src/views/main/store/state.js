// import { busNum } from "@/views/chat/store/getters"

const state = () => ({
  nickname: '',
  mostlyBus: [],
  recentlyBus: [],
  randomBus: [],
  isRouletted: '',

  // 낭만보고서 들어갈 내용
  reportsDetail: {
    id: 1, //보고서id
    bus: '5007', //버스번호
    createDay: '2022-08-11', //보고서작성날짜
    createTime: '22:24:00', //보고서작성시간
    content: '저 오늘 첫 출근이에요 힘내라고 빔 좀 쏴주세요 비이이이임~~!!!!', //좋아요많이받은채팅
    totalChatCount: 800, //총채팅수
    myAccessHour: 1, //접속시간
    myAccessMinute: 15, //접속분
    totalUserCount: 40, //이용유저수
    chatPerMinute: 10, //채팅방화력(분당)
    accumulateUserCount: 500, //여태까지의 유저수
    personalCount: 20, //버스이용수
    boardCount: 20, //버스방명록수
  },
  // 보고서id당 버스번호 저장해놔야함
  busNumSave: []
})
export default state