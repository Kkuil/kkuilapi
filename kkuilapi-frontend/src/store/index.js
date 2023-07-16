import { configureStore } from '@reduxjs/toolkit'
import adminReducer from './modules/admin'

export const store = configureStore({
    reducer: {
        admin: adminReducer,
    },
})