print chr(41 + 24)
arch = [42, 41, 41, 42, 43, 42, 84, 43, 82, 87, 43, 80]
st = {}
for i in range(41, 45):
    st[i] = chr(i + 24)
st[80] = ''
count = 81

codeword = arch.pop(0)
val = st[codeword]
res = ''

while True:
    res += val
    codeword = arch.pop(0)
    if codeword == 80:
        break
    s = st[codeword]
    if count == codeword:
        s = val + val[0]
    st[count] = val + s[0]
    count += 1
    val = s
print res
print st
