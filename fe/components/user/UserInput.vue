<template>
  <el-form-item :label="label" :type="type" :rules="rule">
    <el-input v-model="name" :placeholder="placeholder" name="name"></el-input>
  </el-form-item>
</template>

<script>
export default {
  props: {
    placeholder: {
      type: String,
      default: 'Input your value',
    },
    type: String, // CONVENTION: content_HEADING => this.$store.state['user].content.heading
    label: String,
    rule: Array,
  },
  computed: {
    name: {
      get() {
        return this.value
      },
      set(value) {
        const key = this.type.split('_')
        console.log(key)
      },
    },
  },
}
</script>
