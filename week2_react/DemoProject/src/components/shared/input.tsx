interface InputProps {
    value: string;
    placeholder?: string;
    type?: "email" | "password" | "text";
    setValue: (value: string) => void;
  }
  
  export function Input({ value, setValue, placeholder, type }: InputProps) {
    return (
      <input
        className="border px-4 py-2 rounded-lg shadow-lg w-full"
        value={value}
        type={type}
        placeholder={placeholder}
        onChange={(e) => setValue(e.target.value)}
      />
    );
  }