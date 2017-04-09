export function saveToLocal (id, key, value) {
  let user = window.localStorage.__user__
  if (!user) {
    user = {}
    user[id] = {}
  } else {
    user = JSON.parse(user)
    if (!user[id]) {
      user[id] = {}
    }
  }
  user[id][key] = value
  window.localStorage.__user__ = JSON.stringify(user)
}

export function loadFromLocal (id, key, defaultValue) {
  let user = window.localStorage.__user__
  if (!user) {
    return defaultValue
  }
  user = JSON.parse(user)[id]
  if (!user) {
    return defaultValue
  }
  let ret = user[key]
  return ret || defaultValue
}

// TODO: will be improved later
export const MOCK_ID = 1
