module Main where

-- qsort [] = []
-- qsort (x:xs) = qsort larger ++ [x] ++ qsort smaller 
--                where
--                  smaller = [a | a <- xs, a <= x]
--                  larger = [b | b <- xs, b > x]

-- n = a `div` length xs
--     where
--       a = 10
      -- xs = [1,2,3,4,5]

data Op = Add | Sub | Mul | Div

instance Show Op where
  show Add = "+"
  show Sub = "-"
  show Mul = "*"
  show Div = "/"

valid :: Op -> Int -> Int -> Bool
valid Add _ _ = True
valid Sub x y = x > y
valid Mul _ _ = True
valid Div x y = x `mod` y == 0

apply :: Op -> Int -> Int -> Int
apply Add x y = x + y
apply Sub x y = x - y
apply Mul x y = x * y
apply Div x y = x `div` y

data Expr = Val Int | App Op Expr Expr
instance Show Expr where
  show (Val n)  = show n
  show (App o l r) = brak l ++ show o ++ brak r
                     where
                       brak (Val n) = show n
                       brak e       = "(" ++ show e ++ ")"

values :: Expr -> [Int]
values (Val n) = [n]
values (App _ l r) = values l ++ values r



main :: IO ()
main = putStrLn "Hello, World"

