import { deepFreeze } from "~/ulits/function";

const initialState = () => ({
  email: '',
  password: ''
})

export default deepFreeze(initialState)
