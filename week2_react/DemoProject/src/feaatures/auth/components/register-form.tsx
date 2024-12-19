import { useEffect, useState } from "react";
import { Input } from "../../../components/shared/input";
import { Button } from "../../../components/shared/button";

export function RegisterForm(){
    const [email,setEmail] = useState("");
    const [password, setPassword] = useState<string>("");
    useEffect(()=>{
        console.log(email);
    },[email]);
    return(
        <div className="flex flex-col gap-y-5 border p-7 bg-white rounded-xl w-[400px]">
            <FormHeader/>
            <FormContent 
                email={email} 
                setEmail={setEmail}
                password={password}
                setPassword={setPassword}/>
        </div>         
    );
}

function FormHeader(){
    return(
        <div>
            <h1 className="text-2xl font-bold">Register</h1>
            <p>Enter your information</p>
        </div>
    );
}

interface FormContentProps{
    email: string;
    password: string;
    setEmail: (email:string) => void;
    setPassword: (password:string)=> void;
}

function FormContent({
    email,
    password,
    setEmail,
    setPassword,
  }: FormContentProps) {
    return (
      <div className="flex flex-col gap-y-5">
        <Input value={email} setValue={setEmail} placeholder="Email" />
        <Input
          value={password}
          setValue={setPassword}
          placeholder="Password"
          type="password"
        />
  
        <Button>Register</Button>
      </div>
    );
  }
