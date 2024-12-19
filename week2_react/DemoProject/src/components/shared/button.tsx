import React from "react";

interface ButtonProps {
  children: React.ReactNode;
  variants?: "primary" | "secondary" | "outline";
}

/**
 * A reusable button component that supports different variants.
 *
 * @param {React.ReactNode} children - The content to be displayed inside the button.
 * @param {string} [variants="primary"] - The variant of the button, which determines its styling.
 *                                        Can be "primary" or any other string for a secondary style.
 * @returns {JSX.Element} The rendered button component.
 */
export function Button({ children, variants = "primary" }: ButtonProps) {
  return (
    <button
      className={`w-full ${
        variants === "primary"
          ? "bg-slate-900 hover:bg-slate-900/80"
          : "bg-teal-600 hover:bg-teal-600/80"
      } bg-slate-900 py-2 text-white rounded-lg `}
    >
      {children}
    </button>
  );
}