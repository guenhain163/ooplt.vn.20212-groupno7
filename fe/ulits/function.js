// defind function
import { cloneDeep } from 'lodash'

export function deepMelt (obj) {
    // return JSON.parse(JSON.stringify(obj))
    return cloneDeep(obj)
}

export function deepFreeze (obj) {
    Object.keys(obj).forEach((prop) => {
        if (obj[prop]
            && (typeof obj[prop] === 'object' || typeof obj[prop] === 'function')
            && !Object.isFrozen(obj[prop])) {
                obj[prop] = deepFreeze(obj[prop])
            }
    })
    return Object.freeze(obj)
}

export default {
    deepMelt,
    deepFreeze
}
