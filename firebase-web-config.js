
// PitchPerfect Web Firebase - Replace with your config from Firebase Console
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
const firebaseConfig = {
  apiKey: "YOUR_API_KEY",
  authDomain: "pitchperfect-xyz.firebaseapp.com",
  projectId: "pitchperfect-xyz",
  storageBucket: "pitchperfect-xyz.appspot.com",
  messagingSenderId: "123456789",
  appId: "1:123456789:web:abcdef"
};
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
export const db = getFirestore(app);
