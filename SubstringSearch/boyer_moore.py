txt = "ENCHMANGOODNIGHTENGLISHMANGOOD"
pat = "SHMANGO"
right = {}
N = len(txt)
M = len(pat)
for j in range(M):
    right[pat[j]] = j
i = 0
while i <= N - M:
    skip = 0
    for j in xrange(M - 1, -1, -1):
        if j == M - 1:
            print txt[i + j],
        if pat[j] <> txt[i + j]:
            skip = max(1, j - right.get(txt[i + j], -1))
            break
    if skip == 0:
        print
        print i
        break
    i += skip
 
   
               
        
    