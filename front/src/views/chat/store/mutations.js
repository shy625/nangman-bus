export function SET_BOARDS(state, boards) { 
  state.board = boards[0]
  state.boardColor = boards[0].color
  state.boardDate = boards[0].createDay
  state.boardTime = boards[0].createTime
  state.boards = boards.reverse()
}

export function SET_BOARDS_CREATE(state, boards) {
  state.board = boards[0]
  state.boardColor = boards[0].color
  state.boardDate = boards[0].createDay
  state.boardTime = boards[0].createTime
  state.boards = boards.reverse().slice(0, -1)
}