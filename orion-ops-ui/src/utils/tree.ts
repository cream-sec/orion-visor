import type { NodeData } from '@/types/global';

// 寻找当前节点
export const findNode = <T extends NodeData>(key: any,
                                             nodes: Array<T>,
                                             keyName = 'key'): T => {
  if (!nodes || !nodes.length) {
    return undefined as unknown as T;
  }
  for (let node of nodes) {
    if (node[keyName] === key) {
      return node;
    }
  }
  // 寻找子级
  for (let node of nodes) {
    if (node.children?.length) {
      const childrenNode = findNode(key, node.children, keyName);
      if (childrenNode) {
        return childrenNode as T;
      }
    }
  }
  return undefined as unknown as T;
};

// 寻找父节点
export const findParentNode = <T extends NodeData>(key: any,
                                                   nodes: Array<T>,
                                                   keyName = 'key',
                                                   parent = (undefined as unknown as T)): T => {
  if (!nodes || !nodes.length) {
    return undefined as unknown as T;
  }
  for (let node of nodes) {
    if (node[keyName] === key) {
      if (parent) {
        return parent;
      } else {
        // 根节点
        return {
          root: true
        } as unknown as T;
      }
    }
  }
  // 寻找子级
  for (let node of nodes) {
    if (node.children?.length) {
      const parentNode = findParentNode(key, node.children, keyName, node);
      if (parentNode) {
        return parentNode as T;
      }
    }
  }
  return undefined as unknown as T;
};

// 级联寻找父节点
export const findParentNodes = <T extends NodeData>(key: any,
                                                    nodes: Array<T>,
                                                    result: Array<T>,
                                                    keyName = 'key',
                                                    parent = ([] as T[])) => {
  if (!nodes || !nodes.length) {
    return;
  }
  for (let node of nodes) {
    if (node[keyName] === key) {
      result.push(...parent);
      return;
    }
  }
  // 寻找子级
  for (let node of nodes) {
    if (node.children?.length) {
      const currentParent = [...parent, node];
      findParentNodes(key, node.children, result, keyName, currentParent);
    }
  }
};

// 检查是否包含子节点 单层
export const hasChildren = <T extends NodeData>(key: string,
                                                nodes: Array<T>,
                                                keyName = 'key'): boolean => {
  if (!nodes || !nodes.length) {
    return false;
  }
  return !!nodes.find(s => s[keyName] === key);
};

// 获取所有节点 key
export const flatNodeKeys = <T extends NodeData, R>(nodes: Array<T>,
                                                    result: Array<R>,
                                                    keyName = 'key') => {
  if (!nodes || !nodes.length) {
    return;
  }
  for (let node of nodes) {
    result.push(node[keyName]);
    flatNodeKeys(node.children, result, keyName);
  }
};

// 获取所有节点
export const flatNodes = <T extends NodeData>(nodes: Array<T>,
                                              result: Array<T>) => {
  if (!nodes || !nodes.length) {
    return;
  }
  nodes.forEach(s => {
    result.push(s);
    flatNodes(s.children, result);
  });
};
