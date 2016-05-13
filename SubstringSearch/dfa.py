pat = "BABABBBC"
M = len(pat)
dfa = {}
abc = "ABC"
def put(c, j, v):
    global dfa, M
    a = dfa.get(c, [0] * M)
    a[j] = v
    dfa[c] = a
    
def get(c, j):
    global dfa, M
    a = dfa.get(c, [0] * M)
    return a[j]

put(pat[0], 0 , 1)
x = 0
for j in xrange(1, M):
    for c in abc:
        put(c, j, get(c, x))
    put(pat[j], j, j + 1)
    x = get(pat[j], x)

print dfa['A']
print dfa['B']
print dfa['C']