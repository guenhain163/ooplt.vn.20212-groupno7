const constant = []

for (let index = 2022; index < 2026; index++) {
  for (let indexTemp = 1; indexTemp < 4; indexTemp++) {
    constant.push({
      'lable': `${index}${indexTemp}`,
      'value': Number(`${index}${indexTemp}`)
    })
  }
}

export default constant
