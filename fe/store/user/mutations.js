const mutations = {
  SET_ATTRIBUTE (state, listValue = []) {
    let box
    let attribute
    let value
    switch (listValue.length) {
      case 2:
        [box, value] = listValue
        state[box] = value
        break
      case 3:
        [box, attribute, value] = listValue
        state[box][attribute] = value
        break
      default:
        break
    }
  },
  SET_EMAIL_USER(state, value) {
    state.email = value
  },
  SET_PASSWORD_USER(state, value) {
    state.password = value
  }
}

export default mutations
