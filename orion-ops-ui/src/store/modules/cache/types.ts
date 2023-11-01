import type { UserQueryResponse } from '@/api/user/user';
import type { MenuQueryResponse } from '@/api/system/menu';
import type { RoleQueryResponse } from '@/api/user/role';
import type { TagQueryResponse } from '@/api/meta/tag';
import type { HostKeyQueryResponse } from '@/api/asset/host-key';
import type { HostIdentityQueryResponse } from '@/api/asset/host-identity';
import type { DictKeyQueryResponse } from '@/api/system/dict-key';

export interface CacheState {
  users: UserQueryResponse[];
  menus: MenuQueryResponse[];
  roles: RoleQueryResponse[];
  hostTags: TagQueryResponse[];
  hostKeys: HostKeyQueryResponse[];
  hostIdentities: HostIdentityQueryResponse[];
  dictKeys: DictKeyQueryResponse[];

  [key: string]: unknown;
}
