string = "QNNNSLZNQLZQQQLNNKLNNBNLLKKNLKQQNNLKLZLLSQKLLZ"
freq = {}
for c in string:
    freq[c] = freq.get(c, 0) + 1
for k, v in freq.items():
    print k, v

tree  = [{'char': k, 'freq': v, 'left': None, 'right': None} 
          for k, v in freq.items()]

while len(tree) > 1:
    tree.sort(key = lambda p: p['freq'])
    left = tree.pop(0)
    right = tree.pop(0)
    tree.append({'char': None, 
                 'freq': left['freq'] + right['freq'],
                 'left': left,
                 'right': right
                 })
tree = tree[0]
print tree

def print_codes(tree, code = ''):
    if tree['char'] <> None:
        print tree['char'] + ' - ' + code
    if tree['left'] <> None:
        print_codes(tree['left'], code + '0')
    if tree['right'] <> None:
        print_codes(tree['right'], code + '1')
        
print_codes(tree)

def compres_char(c, tree, code = ''):
    if tree['char'] == c:
        return code
    if tree['left'] <> None:
        a = compres_char(c, tree['left'], code + '0')
        if a <> None:
            return a
    if tree['right'] <> None:
        a = compres_char(c, tree['right'], code + '1')
        if a <> None:
            return a
    return None
    
result = ''
for c in string:
    result += compres_char(c, tree)

print result
print len(result)
    

    