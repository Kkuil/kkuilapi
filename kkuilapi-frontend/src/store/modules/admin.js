import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    info: {
        id: null,
        account: null
    },
}

export const adminSlice = createSlice({
    name: 'admin',
    initialState,
    reducers: {
        setInfo: (state, action) => {
            state.info = action.payload
        }
    },
})

export const { setInfo } = adminSlice.actions

export default adminSlice.reducer