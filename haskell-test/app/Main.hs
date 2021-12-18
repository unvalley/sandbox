module Main where

import qualified MyLib (someFunc)
import Data.List
import Data.Function

-- マス（座標）
type Cell = (Int, Int)
-- 盤面 e.g. ((2,1), 3) = 座標(2,1)のマスに数値3が入っている状態
type Board = [(Cell, Int)]

-- ソルバ
solve :: Board -> [Board]
solve board | length board == 81 = [board]
solve board = [(cell, n) : board
              | let remains = cells \\ map fst board
              , let cell = maximumBy (compare `on` length . used board) remains
              , n <- [1..9] \\ used board cell
              ] >>= solve

-- 81マス全体
cells :: [Cell]
cells = [(x,y) | x <- [0..8], y <- [0..8]]

-- ある盤面状況で，あるマスの周囲に使われている数値を列挙する
-- snd = col, fst = row
used :: Board -> Cell -> [Int]
used board cell = nub [n
                  | (cell', n) <- board
                  -- cell' がcellの周囲に相当する
                  -- 行が同じ・列が同じ・3x3の区画が同じ　のどれかを満たす他のマスのこと
                  , any (\f -> f cell == f cell') [snd, fst, area]
                  ]

area :: Cell -> Int
area (x, y) = y `div` 3 * 3 + x `div` 3

-- ここから開始
main :: IO ()
main = case solve problem of
        answer :_ -> mapM_ print $ format answer
        []        -> putStrLn "invalid problem"

format :: Board -> [[Int]]
format = map (map snd) . transpose . groupBy ((==) `on` (fst . fst)) . sort

-- 数独問題（Boardの初期値）
problem :: Board
problem = [((3,0), 8)
            ,((5,0), 1)]

