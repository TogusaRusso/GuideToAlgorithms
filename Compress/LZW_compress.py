input = "BAABACCBBBAABAC"
st = {}
for i in range(41, 45):
    st[i] = chr(i + 24)
count = 81

def l_p(s, st):
    max = (0, 0)
    for k, v in st.items():
        if len(v) > len(s):
            continue
        if v == s[:len(v)] and len(v) > max[0]:
            max = (len(v), k)
    return max[1]

while len(input) > 0:
    code = l_p(input, st)
    print code,
    t = len(st[code])
    if t < len(input):
        st[count] = input[:t + 1]
        count += 1
    input = input[t:]
print 80
print st
