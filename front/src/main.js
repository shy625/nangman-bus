import { createApp, h } from 'vue'
import App from './App.vue'


// lib
import router from './common/lib/vue-router'
import store from './common/lib/store'
import VueAxios from './common/lib/axios'
import axios from './common/lib/axios'

import ElementPlus from './common/lib/element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/display.css'

const app = createApp({
  render: () => h(App)
})

app.use(router)
app.use(VueAxios, axios)
app.use(store)
app.use(ElementPlus)


app.mount('#app')


// // Element UI Components [시작]
// import {
//   ElAlert,
//   ElAside,
//   ElAutocomplete,
//   ElAvatar,
//   ElBacktop,
//   ElBadge,
//   ElBreadcrumb,
//   ElBreadcrumbItem,
//   ElButton,
//   ElButtonGroup,
//   ElCalendar,
//   ElCard,
//   ElCarousel,
//   ElCarouselItem,
//   ElCascader,
//   ElCascaderPanel,
//   ElCheckbox,
//   ElCheckboxButton,
//   ElCheckboxGroup,
//   ElCol,
//   ElCollapse,
//   ElCollapseItem,
//   ElCollapseTransition,
//   ElColorPicker,
//   ElContainer,
//   ElDatePicker,
//   ElDialog,
//   ElDivider,
//   ElDrawer,
//   ElDropdown,
//   ElDropdownItem,
//   ElDropdownMenu,
//   ElFooter,
//   ElForm,
//   ElFormItem,
//   ElHeader,
//   ElIcon,
//   ElImage,
//   ElInput,
//   ElInputNumber,
//   ElLink,
//   ElMain,
//   ElMenu,
//   ElMenuItem,
//   ElMenuItemGroup,
//   ElOption,
//   ElOptionGroup,
//   ElPageHeader,
//   ElPagination,
//   ElPopconfirm,
//   ElPopover,
//   ElPopper,
//   ElProgress,
//   ElRadio,
//   ElRadioButton,
//   ElRadioGroup,
//   ElRate,
//   ElRow,
//   ElScrollbar,
//   ElSelect,
//   ElSkeleton,
//   ElSkeletonItem,
//   ElSlider,
//   ElStep,
//   ElSteps,
//   ElSubmenu,
//   ElSwitch,
//   ElTabPane,
//   ElTable,
//   ElTableColumn,
//   ElTabs,
//   ElTag,
//   ElTimePicker,
//   ElTimeSelect,
//   ElTimeline,
//   ElTimelineItem,
//   ElTooltip,
//   ElTransfer,
//   ElTree,
//   ElUpload,
//   ElInfiniteScroll,
//   ElLoading,
//   ElMessage,
//   ElMessageBox,
//   ElNotification,
// } from 'element-plus';

// const components = [
//   ElAlert,
//   ElAside,
//   ElAutocomplete,
//   ElAvatar,
//   ElBacktop,
//   ElBadge,
//   ElBreadcrumb,
//   ElBreadcrumbItem,
//   ElButton,
//   ElButtonGroup,
//   ElCalendar,
//   ElCard,
//   ElCarousel,
//   ElCarouselItem,
//   ElCascader,
//   ElCascaderPanel,
//   ElCheckbox,
//   ElCheckboxButton,
//   ElCheckboxGroup,
//   ElCol,
//   ElCollapse,
//   ElCollapseItem,
//   ElCollapseTransition,
//   ElColorPicker,
//   ElContainer,
//   ElDatePicker,
//   ElDialog,
//   ElDivider,
//   ElDrawer,
//   ElDropdown,
//   ElDropdownItem,
//   ElDropdownMenu,
//   ElFooter,
//   ElForm,
//   ElFormItem,
//   ElHeader,
//   ElIcon,
//   ElImage,
//   ElInput,
//   ElInputNumber,
//   ElLink,
//   ElMain,
//   ElMenu,
//   ElMenuItem,
//   ElMenuItemGroup,
//   ElOption,
//   ElOptionGroup,
//   ElPageHeader,
//   ElPagination,
//   ElPopconfirm,
//   ElPopover,
//   ElPopper,
//   ElProgress,
//   ElRadio,
//   ElRadioButton,
//   ElRadioGroup,
//   ElRate,
//   ElRow,
//   ElScrollbar,
//   ElSelect,
//   ElSkeleton,
//   ElSkeletonItem,
//   ElSlider,
//   ElStep,
//   ElSteps,
//   ElSubmenu,
//   ElSwitch,
//   ElTabPane,
//   ElTable,
//   ElTableColumn,
//   ElTabs,
//   ElTag,
//   ElTimePicker,
//   ElTimeSelect,
//   ElTimeline,
//   ElTimelineItem,
//   ElTooltip,
//   ElTransfer,
//   ElTree,
//   ElUpload,
// ]

// const plugins = [
//   ElInfiniteScroll,
//   ElLoading,
//   ElMessage,
//   ElMessageBox,
//   ElNotification,
// ]
// Element UI Components [끝]

// components.forEach(component => {
//   app.component(component.name, component)
// })

// plugins.forEach(plugin => {
//   app.use(plugin)
// })