import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { RegisterForm } from './feaatures/auth/components/register-form'

function App() {

  return (
     <div className='bg-gradient-to-br from-gray-900 to-gray-500 min-h-screen flex justify-center items-center'>
      <RegisterForm />     
     </div>
  )
}

export default App
