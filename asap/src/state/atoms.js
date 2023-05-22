import { atom } from "recoil";
import { recoilPersist } from"recoil-persist";

const { persistAtom } = new recoilPersist({
    key: 'recoil-persist',
    storage: sessionStorage
})

export const isLogin = atom({
    key: "loginState",
    default: false,
    effects_UNSTABLE: [persistAtom]
})