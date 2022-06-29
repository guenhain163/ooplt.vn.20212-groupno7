import actions from "./actions";
import getters from "./getters";
import mutations from "./mutations";
import initialState from "./state";
import { deepMelt } from "~/ulits/function";

export const state = deepMelt(initialState)

export default {
  namespaced: true,
  actions,
  getters,
  mutations,
  state
}
