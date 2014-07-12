data Natural = Zero | Succ Natural deriving (Show)


predecesor (Succ x) = x

equal Zero Zero = True
equal (Succ _) Zero = False
equal Zero (Succ _) = False
equal a b = equal (predecesor a) (predecesor b)

add x Zero = x
add Zero x = x

add x y = Succ (add x (predecesor y))

substract x Zero = x
substract x y = substract (predecesor x) (predecesor y)

lt (Succ _) Zero = False
lt Zero (Succ _) = True
lt x y = lt (predecesor x) (predecesor y)

mult _ Zero = Zero
mult Zero _ = Zero
mult x (Succ Zero) = x
mult (Succ Zero) x = x
mult x y = add x ( mult x (predecesor y))

one = Succ Zero
two = Succ one
three = Succ two
four = Succ three

data MyList a = Null | Cons a (MyList a)


