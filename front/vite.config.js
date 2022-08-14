import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
  ],
  define: {
    'global': {},
  },
  resolve: {
    alias: [
      {
        find: 'eventsource',
        replacement: './node_modules/sockjs-client/lib/transport/browser/eventsource.js',
      },
      {
        find: 'events',
        replacement: './node_modules/sockjs-client/lib/event/emitter.js',
      },
      {
        find: 'crypto',
        replacement: './node_modules/sockjs-client/lib/utils/browser-crypto.js',
      },
    ],
  },
})
